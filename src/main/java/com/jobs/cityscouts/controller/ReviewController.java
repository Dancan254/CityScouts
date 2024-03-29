package com.jobs.cityscouts.controller;

import com.jobs.cityscouts.entity.Review;
import com.jobs.cityscouts.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Operation(summary = "get all reviews posted")
    @ApiResponses(
            value  = {
                @ApiResponse(responseCode = "200", description = "reviews fetched successfully",
                    content = {
                        @Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Review.class)))
                    }
                )

            }
    )
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping("/review")
    public ResponseEntity<String> postReview(@PathVariable Long companyId,
                                             @RequestBody Review review){
        boolean isReviewSaved = reviewService.postReview(companyId,review);
        if (isReviewSaved)
            return new ResponseEntity<>("Successfully created and submitted the review", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);//when company is not found
    }
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,
                                                @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(companyId, reviewId), HttpStatus.OK);

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId,reviewId);
        if (isReviewDeleted)
            return new ResponseEntity<>("Successfully deleted the review", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Failed to delete review", HttpStatus.NOT_FOUND);
    }
}
