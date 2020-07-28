package com.lambdaschool.anywherefitness.controllers;


import com.lambdaschool.anywherefitness.models.Cart;
import com.lambdaschool.anywherefitness.models.Product;
import com.lambdaschool.anywherefitness.models.User;
import com.lambdaschool.anywherefitness.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController
{
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user", produces = {"application/json"})
    public ResponseEntity<?> listAllCarts(Authentication authentication) {

        User u = userService.findByName(authentication.getName());

        List<Cart> myCarts = cartService.findAllByUserId(u.getUserid());
        return new ResponseEntity<>(myCarts, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/cart/{cartId}",
            produces = {"application/json"})
    public ResponseEntity<?> getCartById(
            @PathVariable
                    Long cartId)
    {
        Cart p = cartService.findCartById(cartId);
        return new ResponseEntity<>(p,
                HttpStatus.OK);
    }

    @PostMapping(value = "/create/product/{productid}")
    public ResponseEntity<?> addNewCart(Authentication authentication,
                                        @PathVariable long productid)
    {
        User u = userService.findByName(authentication.getName());

        Product dataProduct = new Product();
        dataProduct.setProductid(productid);

        cartService.save(u, dataProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/cart/{cartid}/product/{productid}")
    public ResponseEntity<?> updateCart(@PathVariable long cartid,
                                        @PathVariable long productid)
    {
        Cart c = cartService.findCartById(cartid);

        Product dataProduct = new Product();
        dataProduct.setProductid(productid);

        cartService.save(c, dataProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/cart/{cartid}/product/{productid}")
    public ResponseEntity<?> deleteFromCart(@PathVariable long cartid,
                                            @PathVariable long productid) {

        Cart c = cartService.findCartById(cartid);

        Product dataProduct = new Product();
        dataProduct.setProductid(productid);

        cartService.delete(c, dataProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}