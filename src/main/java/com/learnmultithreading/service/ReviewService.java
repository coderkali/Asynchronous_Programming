package com.learnmultithreading.service;


import com.learnmultithreading.domain.Review;

import static com.learnmultithreading.util.CommonUtil.delay;


public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        return new Review(200, 4.5);
    }
}
