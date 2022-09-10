package com.repository.admin;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.admin.OfferBean;

@Repository
public interface OfferRepository extends CrudRepository<OfferBean,String>  {
	OfferBean findByOfferId(Integer offerId);
}
