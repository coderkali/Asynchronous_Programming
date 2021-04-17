package com.learnmultithreading.executor;


import com.learnmultithreading.domain.Product;
import com.learnmultithreading.domain.ProductInfo;
import com.learnmultithreading.domain.Review;
import com.learnmultithreading.service.ProductInfoService;
import com.learnmultithreading.service.ReviewService;

import java.util.concurrent.*;

import static com.learnmultithreading.util.CommonUtil.stopWatch;
import static com.learnmultithreading.util.LoggerUtil.log;


public class ProductServiceUsingExecutor {

    public static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceUsingExecutor(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) throws ExecutionException, InterruptedException, TimeoutException {
        stopWatch.start();

        Future<ProductInfo> productInfoFuture = executorService.submit(() -> productInfoService.retrieveProductInfo(productId));
        Future<Review> reviewFuture = executorService.submit(() -> reviewService.retrieveReviews(productId));

        //ProductInfo productInfo= productInfoFuture.get();
        ProductInfo productInfo= productInfoFuture.get(2, TimeUnit.SECONDS);
        Review review = reviewFuture.get();

                //ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
        //Review review = reviewService.retrieveReviews(productId); // blocking call

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingExecutor productService = new ProductServiceUsingExecutor(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);
        executorService.shutdown();

    }
}
