package com.jobs.cityscouts.service;

import com.jobs.cityscouts.entity.Review;
import com.jobs.cityscouts.entity.companyEntity.Company;
import com.jobs.cityscouts.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private  ReviewRepository reviewRepository;
    @Autowired
    private  CompanyService companyService;
    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean postReview(Long companyId, Review review) {
        Company company = companyService.findCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }
    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                        .filter(review -> review.getId().equals(reviewId))
                        .findFirst()
                        .orElse(null);
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.findCompanyById(companyId) != null &&
                reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviewList().remove(review);
            review.setCompany(null);
            companyService.updateCompanyDetails(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }else {
            return false;
        }
    }
}