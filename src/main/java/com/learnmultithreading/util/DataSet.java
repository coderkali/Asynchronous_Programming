package com.learnmultithreading.util;


import com.learnmultithreading.domain.checkout.Cart;
import com.learnmultithreading.domain.checkout.CartItem;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataSet {

    public static List<String> lowerCaseAlphabetList = Arrays.asList("a", "b", "c", "a", "d", "e", "f", "e", "g", "h", "i");

    public static Cart createCart(int noOfItemsInCart) {

        Cart cart = new Cart();
        List<CartItem> cartItemList = new ArrayList<>();
        IntStream.rangeClosed(1, noOfItemsInCart)
                .forEach((index) -> {
                    String cartItem = "CartItem -".concat(index + "");
                    CartItem cartItem1 = new CartItem(index, cartItem, generateRandomPrice(), index, false);
                    cartItemList.add(cartItem1);
                });
        cart.setCartItemList(cartItemList);
        return cart;
    }

    public static List<String> namesList() {
        return Arrays.asList("Bob", "Jamie", "Jill", "Rick");

    }

    public static List<Integer> generateIntegerList(int maxNumber) {
        return IntStream.rangeClosed(1, maxNumber)
                .boxed().collect(Collectors.toList());
    }

    public static ArrayList<Integer> generateArrayList(int maxNumber) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        IntStream.rangeClosed(1, maxNumber)
                .boxed()
                .forEach((arrayList::add));
        return arrayList;
    }

    public static LinkedList<Integer> generateIntegerLinkedList(int maxNumber) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        IntStream.rangeClosed(1, maxNumber)
                .boxed()
                .forEach((linkedList::add));
        return linkedList;
    }

    public static Set<Integer> generateIntegerSet(int maxNumber) {
        return IntStream.rangeClosed(1, maxNumber)
                .boxed().collect(Collectors.toSet());
    }



    public static double generateRandomPrice() {
        int min = 50;
        int max = 100;
        return Math.random() * (max - min + 1) + min;
    }
}
