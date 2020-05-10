package com.concretepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IArticleDAO;
import com.concretepage.entity.Article;

@Service
public class ArticleService implements IArticleService {
	
	@Autowired
	private IArticleDAO iaDao;

	public Article getArticleById(int articleId) {
		Article obj = iaDao.getArticleById(articleId);
		return obj;
	}	
	
	public List<Article> getAllArticles(){
		return iaDao.getAllArticles();
	}
	
	public synchronized boolean addArticle(Article article){
               if (iaDao.articleExists(article.getTitle(), article.getCategory())) {
    	          return false;
               } else {
    	          iaDao.addArticle(article);
    	          return true;
               }
	}
	
	public void updateArticle(Article article) {
		iaDao.updateArticle(article);
	}
	
	public void deleteArticle(int articleId) {
		iaDao.deleteArticle(articleId);
	}
}
