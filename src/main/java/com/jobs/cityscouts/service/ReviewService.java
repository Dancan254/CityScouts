package com.jobs.cityscouts.service;


import com.jobs.cityscouts.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean postReview(Long companyId, Review review);
}
