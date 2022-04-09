package bao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.Entity;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Entity
public class FileuploadController {
	@RequestMapping("/uploadview")
	public ModelAndView upLoadview()
	{
		return new ModelAndView();
	}
	@RequestMapping("/uploadlogic")
	public ModelAndView uploadLogic(@RequestParam CommonsMultipartFile file,HttpSession session)throws IOException
	{
		String path=session.getServletContext().getRealPath("/")+"images";
		String filename=file.getOriginalFilename();
		System.out.println(path+" "+filename);
		byte []bytes=file.getBytes();
		BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(new File (filename)));
		stream.write(bytes);
		stream.flush();
		stream.close();
		return new ModelAndView("uploadform","filesuccess",filename);
	}
	
}
