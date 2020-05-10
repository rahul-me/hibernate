package com.concretepage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.concretepage.entity.Article;

public class ArticleDAO implements IArticleDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public List<Article> getAllArticles() {
		String hql = "from Article as artcl order by artcl.articleId";
		List<?> list = hibernateTemplate.find(hql);
		return (List<Article>)list;
	}

	public Article getArticleById(int articleId) {
		return hibernateTemplate.get(Article.class, articleId);
	}

	public void addArticle(Article article) {
		hibernateTemplate.save(article);
	}

	public void updateArticle(Article article) {
		Article artcl = getArticleById(article.getArticleId());
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		hibernateTemplate.update(artcl);
	}

	public void deleteArticle(int articleId) {
		hibernateTemplate.delete(getArticleById(articleId));
	}

	public boolean articleExists(String title, String location) {
		String hql = "FROM Article as atcl WHERE atcl.title = ? and atcl.category = ?";
		@SuppressWarnings("unchecked")
		List<Article> articles = (List<Article>) hibernateTemplate.find(hql, title, location);
		return articles.size() > 0 ? true : false;
	}

}
