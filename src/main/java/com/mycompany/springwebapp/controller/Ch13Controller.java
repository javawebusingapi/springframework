package com.mycompany.springwebapp.controller;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springwebapp.Interceptor.Login;
import com.mycompany.springwebapp.dao.Ch13MemberDao;
import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Member;
import com.mycompany.springwebapp.dto.Ch13Pager;
import com.mycompany.springwebapp.service.Ch13BoardService;
import com.mycompany.springwebapp.service.Ch13MemberService;
import com.mycompany.springwebapp.service.Ch13MemberService.JoinResult;
import com.mycompany.springwebapp.service.Ch13MemberService.LoginResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13") //이 컨트롤러는 ch13이 포함이된다면 선택이 된다. 뒤의 메소드는 ch13을 빼버려도 된다.
public class Ch13Controller {
	//같은 인터페이스를 사용하고, 서비스 어노테이션이 있다면 자신만의 이름을 부여하거나 default시 앞자리 소문자로 바꾼 상태로 name을 준다.
	@Resource/*(name="chMemberServiceImpl")*/
	private Ch13BoardService boardService;
	
	@Resource
	private Ch13MemberService memberService;
	
	@Value("${file.upload.path}")
	private String fileUploadDir;
	
	/*@Resource
	//인터페이스기 때문에 구현객체가 필요하지만 Mybatis가 구현객체를 알아서 만들어줌
	private Ch13BoardDao boardDao;*/
	
	@Resource
	private Ch13MemberDao memberDao;
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch13 content 실행");
		return "ch13/content";
	}
	
	@GetMapping("/writeBoard")
	@Login
	public String writeBoardForm() {
		
		return "ch13/writeBoardForm";
	}
	
	@PostMapping("/writeBoard")
	@Login
	public String writeBoard (Ch13Board board, HttpSession session) throws Exception{
		log.info("writeBoard 실행");
		Ch13Member member = (Ch13Member) session.getAttribute("ch13Login");
		board.setMid(member.getMid());
		
		MultipartFile mf = board.getBattach();
		if(!mf.isEmpty()) {
			//브라우저에서 선택한 파일 이름으로 설정
			board.setBattachoname(mf.getOriginalFilename());
			
			//파일의 형식 (MIME 타입)을 설정 (image/jpeg, image/png 등등..)
			board.setBattachtype(mf.getContentType());
			
			//방법1) 첨부파일을 서버 파일 시스템에 저장한다.
			String saveFileName = new Date().getTime() + "-" + mf.getOriginalFilename();
			board.setBattachsname(saveFileName);
			
			File file = new File(fileUploadDir + "/" + saveFileName);
			mf.transferTo(file);
			
			//방법2) 첨부파일을 DB에 직접 저장한다.
			//단점 : 메모리에 직접 저장을 하는 것이기 떄문에 파일의 크기가 크면 클수록 메모리를 더 많이 차지해버린다. (효율적이지 않음)
			/*board.setBattachdata(mf.getBytes());*/
		} 
		
		/*for(int i=1; i<=10000; i++) {
			Ch13Board board = new Ch13Board();
			board.setBtitle("글이 너무 많아"+i);
			board.setBcontent("내용도 너무 많아"+i);
			board.setMid("user");
			
			//boardDaoOld.insert(board);
			boardDao.insert(board);
			
			//실제로 저장된 bno
			log.info("저장된 bno : " + board.getBno());
		}*/

		/*//직접 추가		
		Ch13Board board = new Ch13Board();
		board.setBtitle("졸리다");
		board.setBcontent("그냥 졸리다");
		board.setMid("user");*/
		
		//boardDaoOld.insert(board);
		boardService.write(board);
		
		//실제로 저장된 bno
		log.info("저장된 bno : " + board.getBno());
		
		return "redirect:/ch13/getboardList";
	}
	
	@GetMapping("/getboardList")
	public String getboardList(String pageNo, Model model, HttpSession session){
		log.info("getboardList 실행");
		//브라우저에서 pageNo가 넘어오지 않았을 경우
		if(pageNo == null) {
			//세션에 저장되어 있는지 확인
			pageNo = (String) session.getAttribute("pageNo");
			if(pageNo == null) {
				//저장되어 있지 않다면 "1"로 초기화
				pageNo = "1";
			}
		}
		//문자열을 정수로 변환
		int intPageNo = Integer.parseInt(pageNo);
		//세션에 pageNo를 저장
		session.setAttribute("pageNo", String.valueOf(pageNo));
		
		int totalBoardNums = boardService.getTotalBoardNum();
		Ch13Pager pager = new Ch13Pager(5,5,totalBoardNums, intPageNo);
		
		//List<Ch13Board> list = boardDaoOld.selectByPage(pager);
		List<Ch13Board> list = boardService.getList(pager);
		
		model.addAttribute("boards", list);
		model.addAttribute("pager",pager);
		
		return "ch13/boardList";
	}
	
	@GetMapping("/detailBoard")
	public String detailBoard(int bno, Model model) {
		Ch13Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		
		//attachdata 이미지 보이게 추가하기
		if(board.getBattachdata() != null) {
			//0과 1로 구성된 바이너리 데이터를 base64 (64bit)문자열로 변경
			String base64Img = Base64.getEncoder().encodeToString(board.getBattachdata());
			model.addAttribute("base64Img", base64Img);
		}
		
		return "ch13/detailBoard";
	}
	
	@GetMapping("/filedownload1")
	public void filedownload1 (int bno, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Ch13Board board = boardService.getBoard(bno);
		
		String fileSaveName = board.getBattachsname();
		String fileOriginalName = board.getBattachoname();
		String filePath = fileUploadDir+"/"+fileSaveName;
				
		//String mimeType = request.getServletContext().getMimeType(filePath);
		String mimeType = board.getBattachtype();
		response.setContentType(mimeType);
	
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			fileOriginalName = URLEncoder.encode(fileOriginalName,"UTF-8");
			log.info(fileOriginalName);
		} else {
			fileOriginalName = new String(fileOriginalName.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition","attachment; filename=\""+fileOriginalName+"\"");
		
		OutputStream os = response.getOutputStream();
		Path path = Paths.get(filePath);
		Files.copy(path,os);
		os.flush();
		os.close();
	}
	
	@GetMapping("/filedownload2")
	public void filedownload2 (int bno, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Ch13Board board = boardService.getBoard(bno);
		
		String fileOriginalName = board.getBattachoname();
				
		//String mimeType = request.getServletContext().getMimeType(filePath);
		String mimeType = board.getBattachtype();
		response.setContentType(mimeType);
	
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			fileOriginalName = URLEncoder.encode(fileOriginalName,"UTF-8");
			log.info(fileOriginalName);
		} else {
			fileOriginalName = new String(fileOriginalName.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition","attachment; filename=\""+fileOriginalName+"\"");
		
		OutputStream os = response.getOutputStream();
		os.write(board.getBattachdata());
		os.flush();
		os.close();
	}
	
	@GetMapping("/updateBoard")
	@Login
	public String updateBoardForm(int bno, Model model,HttpSession session) {
		log.info("updateBoard 실행");
		//Select를 먼저 해준 다음, update를 해야함 (이것을 안하면 update시 다른 컬럼은 null이기 떄문에 필요한 bno만 수정)
		//Ch13Board board = boardDaoOld.selectByBno(2);
		Ch13Board board = boardService.getBoard(bno);
		
		//아이디 다르면 수정 삭제 못하게 하는 코드(방법중 하나)
		/*Ch13Member member = (Ch13Member) session.getAttribute("ch13Login");
		if(!member.getMid().equals(board.getMid())) {
			return "redirect:/ch13/getBoardList";
		}*/
		
		model.addAttribute("board", board);
		//boardDaoOld.updateByBno(board);
		//boardService.modify(board);
		return "ch13/updateBoardForm";
	}
	@PostMapping("/updateBoard")
	@Login
	public String updateBoard(Ch13Board board, Model model) {
		log.info("updateBoard 실행");
		//Select를 먼저 해준 다음, update를 해야함 (이것을 안하면 update시 다른 컬럼은 null이기 떄문에 필요한 bno만 수정)
		//Ch13Board board = boardDaoOld.selectByBno(2);
		boardService.modify(board);
		//boardDaoOld.updateByBno(board);
		//boardService.modify(board);
		return "redirect:/ch13/getboardList";
	}
	
	@GetMapping("/deleteBoard")
	@Login
	public String deleteBoard(int bno, Model model) {
		log.info("deleteBoard 실행");
		//boardDaoOld.deleteByBno(bno);
		boardService.remove(bno);
		return "redirect:/ch13/getboardList";
	}
	
	@GetMapping("/join")
	public String joinForm() {
		
		return "ch13/joinForm";
	}
	
	//Controller에 응답제공및 처리까지 전부다 작성한 경우(이렇게 쓰지 말고, 처리는 Service에서 작성하는게 좋음)
	/*@PostMapping("join")
	public String join(Ch13Member member, Model model) {
		Ch13Member dbMember = memberService.getMember(member.getMid());
		if(dbMember != null) {
			String error = "중복된 아이디입니다.";
			model.addAttribute("error", error);
			return "ch13/joinForm";
		} 
		
		memberService.join(member);
		return "redirect:/ch13/content";
	}*/
	
	//MemberService쪽에서 join을 작성하고 결과(응답)만 보여주는게 훨씬 Controller 다운 코드가 된다.
	@PostMapping("join")
	public String join(Ch13Member member, Model model) {
		JoinResult result = memberService.join(member);
		if(result == JoinResult.FAIL_DUPLICATED_MID) {
			String error = "중복된 아이디입니다.";
			model.addAttribute("error", error);
			return "ch13/joinForm";
		} else {
			memberService.join(member);
			return "redirect:/ch13/content";
		}
	}
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "ch13/loginForm";
	}
	
	/*@PostMapping("login")
	public String login(Ch13Member member, Model model, HttpSession session) {
		LoginResult result = memberService.login(member);
		if(result == LoginResult.FAIL_MID) {
			String error = "아이디가 일치하지 않습니다.";
			model.addAttribute("error", error);
			return "ch13/loginForm";
		} else if (result == LoginResult.FAIL_MENABLED){
			String error = "휴면 계정입니다.";
			model.addAttribute("error", error);
			return "ch13/loginForm";
		} else if (result == LoginResult.FAIL_MPASSWORD){
			String error = "비밀번호가 일치하지 않습니다.";
			model.addAttribute("error", error);
			return "ch13/loginForm";
		} else {
			return "ch13/content";
		}
		
		Ch13Member dbMember = memberService.getMember(member.getMid());
		session.setAttribute("ch13Login",dbMember);
		memberService.login(member);
		return "redirect:/ch13/content";
	}*/
	@PostMapping("/login")
	public String login(Ch13Member member, Model model, HttpSession session) {
		LoginResult result = memberService.login(member);
		String error = "";
		if(result == LoginResult.FAIL_MID) {
			error = "아이디가 일치하지 않습니다.";
		} else if (result == LoginResult.FAIL_MENABLED){
			error = "휴면 계정입니다.";
		} else if (result == LoginResult.FAIL_MPASSWORD){
			error = "비밀번호가 일치하지 않습니다.";
		} else {
			Ch13Member dbMember = memberService.getMember(member.getMid());
			session.setAttribute("ch13Login",dbMember);
			
			return "redirect:/ch13/content";
		}
		
		model.addAttribute("error",error);
		return "ch13/loginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("ch13Login");
		return "redirect:/ch13/content";
	}
	
	
}