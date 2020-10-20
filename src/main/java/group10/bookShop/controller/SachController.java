package group10.bookShop.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;

import group10.bookShop.entities.Kinhte;
import group10.bookShop.entities.Kynangsong;
import group10.bookShop.entities.Nuoidaycon;
import group10.bookShop.entities.Sach;
import group10.bookShop.entities.Sachgiaokhoa;
import group10.bookShop.entities.Sachngoaingu;
import group10.bookShop.entities.Thieunhi;
import group10.bookShop.entities.Vanhocnuocngoai;
import group10.bookShop.entities.Vanhoctrongnuoc;
import group10.bookShop.service.kinhte.KinhteService;
import group10.bookShop.service.kynangsong.KynangsongService;
import group10.bookShop.service.nuoidaycon.NuoidayconService;
import group10.bookShop.service.sach.SachService;
import group10.bookShop.service.sachgiaokhoa.SachgiaokhoaService;
import group10.bookShop.service.sachngoaingu.SachngoainguService;
import group10.bookShop.service.thieunhi.ThieunhiService;
import group10.bookShop.service.vanhocnuocngoai.VanhocnuocngoaiService;
import group10.bookShop.service.vanhoctrongnuoc.VanhoctrongnuocService;

@Controller
public class SachController {
	
	@Autowired(required = true)
	private SachService bookService;
	
	@Autowired(required = true)
	private KinhteService kinhteService;
	
	@Autowired(required = true)
	private KynangsongService kynangsongService;

	@Autowired(required = true)
	private NuoidayconService nuoidayconService;
	
	@Autowired(required = true)
	private SachgiaokhoaService sachgiaokhoaService;
	
	@Autowired(required = true)
	private SachngoainguService sachngoainguService;
	
	@Autowired(required = true)
	private ThieunhiService thieunhiService;
	
	@Autowired(required = true)
	private VanhocnuocngoaiService vanhocnuocngoaiService;
	
	@Autowired(required = true)
	private VanhoctrongnuocService vanhoctrongnuocService;
	
	@GetMapping("/book")
	public String list(Model model) {
		try {
			model.addAttribute("books", bookService.findAll()); // controller gọi service yêu cầu dữ liệu.
			
		}catch(NullPointerException e) {
			model.addAttribute("errorMessage", e);
		}

		return "list";
	}
	
	@GetMapping("/book/search")
	public String search (@RequestParam("name") String name, Model model) {
		if(org.springframework.util.StringUtils.isEmpty(name)) {
			return "redirect:/book";
		}
//		System.out.println(contactService.search(name));
		model.addAttribute("books", bookService.search(name));
		return "list";
	}
	
	@GetMapping("/book/add")
	public String add(Model model) {
	    model.addAttribute("book", new Sach());  // khởi tạo mới 1 đối tượng và gửi lên form Mỗi thuộc tính của contact tương ứng với một input trong form.
	    return "form";
	}
	
	@PostMapping("/book/save")
	public String save(@Valid @ModelAttribute("book") Sach book, BindingResult result, RedirectAttributes redirect) {  // @Valid Contact contact để kích hoạt cơ chế validation cho contact(trong entities ), lấy result làm biding
	    if (result.hasErrors()) {
	        return "form";
	    }
	    book.setNgayxuatban(LocalDateTime.now());
	    
	    switch(book.getTheloai()) {
		    case "kinhte" :
		    	Kinhte kinhte = new Kinhte(book.getMasach(), book.getTensach(), book.getTacgia(), book.getTheloai(), book.getGiaca(), book.getMota(),
		    			book.getHinhanh(), book.getTonkho(), book.getSoluongdaban(), book.getNgayxuatban());
		    	
			    kinhteService.save(kinhte);
			    bookService.save(book);
			    break;
			    
		    case "nuoidaycon" :
		    	Nuoidaycon nuoidaycon = new Nuoidaycon(book.getMasach(), book.getTensach(), book.getTacgia(), book.getTheloai(), book.getGiaca(), book.getMota(),
		    			book.getHinhanh(), book.getTonkho(), book.getSoluongdaban(), book.getNgayxuatban());
		    	
		    	nuoidayconService.save(nuoidaycon);
			    bookService.save(book);
			    break;
			    
		    case "kynangsong" :
		    	Kynangsong kynangsong = new Kynangsong(book.getMasach(), book.getTensach(), book.getTacgia(), book.getTheloai(), book.getGiaca(), book.getMota(),
		    			book.getHinhanh(), book.getTonkho(), book.getSoluongdaban(), book.getNgayxuatban());
		    	
		    	kynangsongService.save(kynangsong);
			    bookService.save(book);
			    break;
			    
		    case "sachgiaokhoa" :
		    	Sachgiaokhoa sachgiaokhoa= new Sachgiaokhoa(book.getMasach(), book.getTensach(), book.getTacgia(), book.getTheloai(), book.getGiaca(), book.getMota(),
		    			book.getHinhanh(), book.getTonkho(), book.getSoluongdaban(), book.getNgayxuatban());
		    	
		    	sachgiaokhoaService.save(sachgiaokhoa);
			    bookService.save(book);
			    break;
			    
		    case "sachngoaingu" :
		    	Sachngoaingu sachngoaingu = new Sachngoaingu(book.getMasach(), book.getTensach(), book.getTacgia(), book.getTheloai(), book.getGiaca(), book.getMota(),
		    			book.getHinhanh(), book.getTonkho(), book.getSoluongdaban(), book.getNgayxuatban());
		    	
		    	sachngoainguService.save(sachngoaingu );
			    bookService.save(book);
			    break;
			    
		    case "thieunhi" :
		    	Thieunhi thieunhi = new Thieunhi(book.getMasach(), book.getTensach(), book.getTacgia(), book.getTheloai(), book.getGiaca(), book.getMota(),
		    			book.getHinhanh(), book.getTonkho(), book.getSoluongdaban(), book.getNgayxuatban());
		    	
		    	thieunhiService.save(thieunhi);
			    bookService.save(book);
			    break;
			    
		    case "vanhocnuocngoai" :
		    	Vanhocnuocngoai vanhocnuocngoai = new Vanhocnuocngoai(book.getMasach(), book.getTensach(), book.getTacgia(), book.getTheloai(), book.getGiaca(), book.getMota(),
		    			book.getHinhanh(), book.getTonkho(), book.getSoluongdaban(), book.getNgayxuatban());
		    	
		    	vanhocnuocngoaiService.save(vanhocnuocngoai);
			    bookService.save(book);
			    break;
			
		    case "vanhoctrongnuoc" :
		    	Vanhoctrongnuoc vahoctrongnuoc = new Vanhoctrongnuoc(book.getMasach(), book.getTensach(), book.getTacgia(), book.getTheloai(), book.getGiaca(), book.getMota(),
		    			book.getHinhanh(), book.getTonkho(), book.getSoluongdaban(), book.getNgayxuatban());
		    	
		    	vanhoctrongnuocService.save(vahoctrongnuoc);
			    bookService.save(book);
			    break;
		    }
	    redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
	    return "redirect:/book";
	}
	
	@GetMapping("/book/{masach}/edit")
	public String edit(@PathVariable("masach") Integer masach, Model model) {
	    model.addAttribute("book", bookService.findById(masach));
	    return "form";
	}
	
	@GetMapping("/book/{masach}/{theloai}/delete")
	public String delete(@PathVariable int  masach, @PathVariable String theloai, RedirectAttributes redirect) {
		
		 switch(theloai) {
		    case "kinhte" :
		    	bookService.delete(masach);
		  	    kinhteService.delete(masach);
			    break;
			    
		    case "nuoidaycon" :
		    	bookService.delete(masach);
		  	    nuoidayconService.delete(masach);
			    break;
			    
		    case "kynangsong" :
		    	bookService.delete(masach);
		  	    kynangsongService.delete(masach);
			    break;
			    
		    case "sachgiaokhoa" :
		    	bookService.delete(masach);
		  	    sachgiaokhoaService.delete(masach);
			    break;
			    
		    case "sachngoaingu" :
		    	bookService.delete(masach);
		  	    sachngoainguService.delete(masach);
			    break;
			    
		    case "thieunhi" :
		    	bookService.delete(masach);
		  	    thieunhiService.delete(masach);
			    break;
			    
		    case "vanhocnuocngoai" :
		    	bookService.delete(masach);
		  	    vanhocnuocngoaiService.delete(masach);
			    break;
			
		    case "vanhoctrongnuoc" :
		    	bookService.delete(masach);
		  	    vanhoctrongnuocService.delete(masach);
			    break;
		    }
//	    bookService.delete(masach);
//	    kinhteService.delete(masach);
	    redirect.addFlashAttribute("successMessage", "Deleted contact successfully!");
	    return "redirect:/book";
	}
}
	
