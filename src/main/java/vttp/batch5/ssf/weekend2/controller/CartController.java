package vttp.batch5.ssf.weekend2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vttp.batch5.ssf.weekend2.model.*;
import vttp.batch5.ssf.weekend2.service.CartService;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String showLanding() {
        return "landing";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId) {
        return "redirect:/carts/" + userId;
    }

    @GetMapping("/carts/{userId}")
    public String showCarts(@PathVariable String userId, Model model) {
        User user = cartService.getUser(userId);
        model.addAttribute("user", user);
        return "carts";
    }

    @PostMapping("/carts/{userId}/new")
    public String createCart(@PathVariable String userId, @RequestParam String cartName) {
        cartService.createCart(userId, cartName);
        return "redirect:/carts/" + userId;
    }

    @GetMapping("/carts/{userId}/{cartName}")
    public String showCart(@PathVariable String userId,
            @PathVariable String cartName,
            Model model) {
        User user = cartService.getUser(userId);
        Cart cart = user.getCarts().get(cartName);
        model.addAttribute("user", user);
        model.addAttribute("cart", cart);
        return "cart-detail";
    }

    @PostMapping("/carts/{userId}/{cartName}/add")
    public String addItem(@PathVariable String userId,
            @PathVariable String cartName,
            @RequestParam String item,
            @RequestParam Integer quantity) {
        cartService.addItem(userId, cartName, item, quantity);
        return "redirect:/carts/" + userId + "/" + cartName;
    }

    @PostMapping("/carts/{userId}/{cartName}/save")
    public String saveCart(@PathVariable String userId,
            @PathVariable String cartName) {
        cartService.saveCart(userId, cartName);
        return "redirect:/carts/" + userId;
    }
}
