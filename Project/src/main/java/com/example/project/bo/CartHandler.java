package com.example.project.bo;

import com.example.project.ui.CartInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CartHandler {
    public static void addItemToCart(String username, String password, int id){
        Cart.addItemToCart(username, password, id);
    }

    public static Collection<CartInfo> getShoppingCart(String username){
        Collection tempCollection = Cart.getShoppingCart(username);
        ArrayList<CartInfo> returnedCart = new ArrayList<CartInfo>();
        for(Iterator it = tempCollection.iterator(); it.hasNext();){
            Cart cart = (Cart) it.next();
            returnedCart.add(new CartInfo(cart.getItemId(), cart.getItemName(), cart.getPrice(), cart.getQuantity()));
        }
        return returnedCart;
    }

    public static boolean removeItemFromCart(String username, int quantity, int itemId){
        return Cart.removeFromUserCart(username, quantity, itemId);
    }
}
