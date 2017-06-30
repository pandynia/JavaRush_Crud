package org.pandynia.javarush.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spr.exception.UsersNotFound;
import com.spr.model.Users;
import com.spr.service.UsersService;
import com.spr.validation.UsersValidator;

@Controller
@RequestMapping(value="/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UsersValidator usersValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		//binder.setValidator(usersValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "createdDate", new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)	
	public ModelAndView newUsersPage() {
		ModelAndView mav = new ModelAndView("users-new", "users", new Users());
		boolean isAdmin = false;
		mav.addObject("isAdmin", isAdmin);
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)		
	public ModelAndView createNewUsers(@ModelAttribute @Valid Users users,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return new ModelAndView("users-new");
		
		ModelAndView mav = new ModelAndView();
		String message = "New users "+ users.getName()+" was successfully created.";
		
		usersService.create(users);
		mav.setViewName("redirect:/index.html");
				
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView usersListPage() {
		ModelAndView mav = new ModelAndView("users-list");
		List<Users> usersList = usersService.findAll();
		//List<Users> usersList = usersService.findByName("R");
		mav.addObject("usersList", usersList);
		return mav;
	}
	
	
	@RequestMapping(value="/pages/{pageNumber}", method=RequestMethod.GET)
	public ModelAndView usersListPaging(@PathVariable Integer pageNumber) {
		Page<Users> page = usersService.getUsersPages(pageNumber);
		
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		    
		ModelAndView mav = new ModelAndView("users-pages");
		mav.addObject("page", page);
		mav.addObject("beginIndex", begin);
		mav.addObject("endIndex", end);
		mav.addObject("currentIndex", current);
		
		List<Users> userListPg = page.getContent();
		mav.addObject("userListPg", userListPg);
		
		return mav;
	}

	@RequestMapping(value="/search-result", method=RequestMethod.GET)
	public ModelAndView usersListSearch(@RequestParam("searchString") String searchString) {
		
		ModelAndView mav = new ModelAndView("users-search-result");
		List<Users> users = null;
		if (searchString.length() != 0) {
			users = usersService.findByName(searchString);
		}
		else
			users = usersService.findAll();
		
		System.out.println(users.size());
		mav.addObject("usersFound", users);				
		return mav;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView usersListSearchPage() {						
		ModelAndView mav = new ModelAndView("users-search");
		return mav;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUsersPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("users-edit");
		Users users = usersService.findById(id);
		mav.addObject("users", users);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView editUsers(@ModelAttribute @Valid Users users,	
			BindingResult result,
			@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws UsersNotFound {		
		if (result.hasErrors())
			return new ModelAndView("users-edit");
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "Users was successfully updated.";		

		usersService.update(users);
		
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteUsers(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws UsersNotFound {
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");		
		
		Users users = usersService.delete(id);
		String message = "The users "+users.getName()+" was successfully deleted.";
		
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
}
