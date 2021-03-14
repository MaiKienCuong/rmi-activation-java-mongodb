package dao.impl;

import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import dao.News_interface;
import entity.News;

public class News_dao extends Activatable implements News_interface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OgmSessionFactory sessionFactory;

	public News_dao(ActivationID id, MarshalledObject<?> data) throws RemoteException {
		super(id, 0);
		sessionFactory = MySessionFactory.getInstance().getSessionFactory();
	}

	/**
	 *
	 */
	@Override
	public News getByID(Long id) throws RemoteException {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		try {
			News news = session.find(News.class, id);
			tran.commit();
			return news;
		} catch (Exception e) {
			tran.rollback();
		}

		return null;
	}

	/**
	 *
	 */
	@Override
	public List<News> getNewsByTagsOrCategoriesName(String value) throws RemoteException {
		List<News> list = new ArrayList<>();
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		String s = "db.news.find({'$text': {'$search': '" + value + "'}})";
		try {
			list = session.createNativeQuery(s, News.class).getResultList();
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		}

		return list;
	}

	@Override
	public List<News> listNewsByUserEmail(String email) throws RemoteException {
		List<News> list = new ArrayList<>();
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		String s = "db.users.aggregate([{'$match': {'userEmail': '" + email
				+ "'}},{'$project': {'_id': 1, 'listOfComments.newID':1}}, {'$unwind': '$listOfComments'}])";
		try {
			List<?> list2 = session.createNativeQuery(s).getResultList();
			for (Object obj : list2) {
				Object[] o = (Object[]) obj;
				Document document = (Document) o[1];
				News news = session.find(News.class, document.getLong("newID"));
				list.add(news);
			}
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		}

		return list;
	}

}
