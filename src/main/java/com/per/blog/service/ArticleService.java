package com.per.blog.service;

import com.per.blog.dao.ArticleDao;
import com.per.blog.dao.ArticleListDao;
import com.per.blog.pojo.Article;
import com.per.blog.pojo.ArticleListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/2/9 0009.
 */
@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;
    @Autowired
    ArticleListDao articleListDao;

    public Article findById(String id) {
        return articleDao.findById(id).get();
    }

    public Page<ArticleListVO> findByMapPage(Map whereMap, int page, int size) {
        Specification<ArticleListVO> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return articleListDao.findAll(specification, pageRequest);
    }

    public void add(Article article) {
        articleDao.save(article);
    }

    public void update(Article article) {
        articleDao.save(article);
    }

    public List<ArticleListVO> findHot() {
        return articleListDao.findTop5ByOrderByThumbsAsc();
    }

    public List<ArticleListVO> findLatest() {
        return articleListDao.findTop5ByOrderByCreateTimeAsc();
    }

    private Specification<ArticleListVO> createSpecification(Map searchMap) {

        return new Specification<ArticleListVO>() {
            @Override
            public Predicate toPredicate(Root<ArticleListVO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (searchMap != null) {
                    if (searchMap.get("userId") != null && !"".equals(searchMap.get("userId"))) {
                        predicateList.add(cb.equal(root.get("user").get("id").as(String.class), "%" + searchMap.get("userId") + "%"));
                    }
                    if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                        predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
                    }
                    if (searchMap.get("categoryId") != null && !"".equals(searchMap.get("categoryId"))) {
                        predicateList.add(cb.equal(root.get("category").get("id"), Integer.parseInt(searchMap.get("categoryId").toString())));
                    }
                }
                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
