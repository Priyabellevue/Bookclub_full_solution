/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub.Web;

/* import jakarta.validation.Valid; */

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.MongoWishlistDao;
import com.bookclub.service.dao.WishlistDao;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

        WishlistDao wishlistDao = new MongoWishlistDao();

        @Autowired
        private void setWishlistDao(WishlistDao wishlistDao){
        this.wishlistDao = wishlistDao;
        }

        @RequestMapping(method = RequestMethod.GET)
        public String showWishlist(Model model) {
        return "wishlist/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {
        WishlistItem wishlistItem = wishlistDao.find(id);

        model.addAttribute("wishlistItem", wishlistItem);

        return "wishlist/view";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        /*System.out.println(wishlistItem.toString());

        System.out.println(bindingResult.getAllErrors());*/
        wishlistItem.setUsername(authentication.getName());

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistDao.add(wishlistItem); // add the record to MongoDB
        return "redirect:/wishlist";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String updateWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication){
        wishlistItem.setUsername(authentication.getName());

        if (bindingResult.hasErrors()) {
            return "wishlist/view";
        }

        wishlistDao.update(wishlistItem);

        return "redirect:/wishlist";
    }

    @RequestMapping(method = RequestMethod.GET, path="/remove/{id}")
    public String removeWishlistItem(@PathVariable String id){
        wishlistDao.remove(id);

        return "redirect:/wishlist";
    }
}