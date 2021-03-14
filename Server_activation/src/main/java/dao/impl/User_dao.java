package dao.impl;

import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import dao.User_interface;
import entity.User;

public class User_dao extends Activatable implements User_interface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OgmSessionFactory sessionFactory;

	public User_dao(ActivationID id, MarshalledObject<?> data) throws RemoteException {
		super(id, 0);
		sessionFactory = MySessionFactory.getInstance().getSessionFactory();
	}

	/**
	 *
	 */
	@Override
	public User getByID(Long id) throws RemoteException {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		try {
			User user = session.find(User.class, id);
			tran.commit();
			return user;
		} catch (Exception e) {
			tran.rollback();
		}

		return null;
	}

	/**
	 *
	 */
	@Override
	public boolean addUser(User u) throws RemoteException {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		try {
			session.save(u);
			tran.commit();
			return true;
		} catch (Exception e) {
			tran.rollback();
		}
		return false;
	}

	/**
	 *
	 */
	@Override
	public Map<User, Integer> getStatistics() throws RemoteException {
		Map<User, Integer> map = new HashMap<>();
		String s = "db.users.aggregate([{'$project': {'_id': 1, 'count': {'$cond': {'if': {'$isArray': '$listOfComments'}, 'then': {'$size': '$listOfComments'}, 'else':0 }}}}, {'$match': {'count': {'$gte': 3}}}])";
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		try {
			List<?> list = session.createNativeQuery(s).getResultList();
			for (Object obj : list) {
				Object[] o = (Object[]) obj;
				User user = session.find(User.class, Long.parseLong(o[0].toString()));
				Integer count = Integer.parseInt(o[1].toString());
				map.put(user, count);
			}
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		}

		return map;
	}

	/**
	 *
	 */
	@Override
	public List<User> listUsers(String keyword) throws RemoteException {
		List<User> list = new ArrayList<>();
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		String s = "db.users.find({'$or': [{'userName': '" + keyword + "'}, {'userEmail': '" + keyword + "'}]})";
		try {
			list = session.createNativeQuery(s, User.class).getResultList();
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		}

		return list;
	}

}
