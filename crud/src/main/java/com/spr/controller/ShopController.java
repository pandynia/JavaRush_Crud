package com.spr.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spr.exception.ShopNotFound;
import com.spr.model.Shop;
import com.spr.service.ShopService;
import com.spr.validation.ShopValidator;

@Controller
@RequestMapping(value="/shop")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ShopValidator shopValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		//binder.setValidator(shopValidator);
		
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView newShopPage() {
		ModelAndView mav = new ModelAndView("shop-new", "shop", new Shop());
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createNewShop(@ModelAttribute @Valid Shop shop,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return new ModelAndView("shop-new");
		
		ModelAndView mav = new ModelAndView();
		String message = "New shop "+shop.getName()+" was successfully created.";
		
		shopService.create(shop);
		mav.setViewName("redirect:/index.html");
				
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}
	
	//@RequestMapping(value="/list", method=RequestMethod.GET)
	@RequestMapping(value="/list/{pageNumber}", method=RequestMethod.GET)	
	public ModelAndView shopListPage(@PathVariable Integer pageNumber) {
	//public ModelAndView shopListPage(@RequestParam(defaultValue="1", value="page", required=false) Integer pageNumber) {
		ModelAndView mav = new ModelAndView("shop-pages");		
		//ModelAndView mav = new ModelAndView("shop-pages");
		Page<Shop> shopPage = shopService.findAll(pageNumber);
        List<Shop> shopList = shopPage.getContent();
        int current = shopPage.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, shopPage.getTotalPages());        
        mav.addObject("page", shopPage);
        mav.addObject("beginIndex", begin);
        mav.addObject("endIndex", end);
        mav.addObject("currentIndex", current);		
		mav.addObject("shopList", shopList);
		return mav;
	}	
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editShopPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("shop-edit");
		Shop shop = shopService.findById(id);
		mav.addObject("shop", shop);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView editShop(@ModelAttribute @Valid Shop shop,
			BindingResult result,
			@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws ShopNotFound {
		
		if (result.hasErrors())
			return new ModelAndView("shop-edit");
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "Shop was successfully updated.";

		shopService.update(shop);
		
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteShop(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws ShopNotFound {
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");		
		
		Shop shop = shopService.delete(id);
		String message = "The shop "+shop.getName()+" was successfully deleted.";
		
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
}
