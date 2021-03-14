package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import entity.User;

public interface User_interface extends Remote {

	/**
	 * get by id
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public User getByID(Long id) throws RemoteException;

	/**
	 * them user
	 * 
	 * @param u
	 * @return
	 * @throws RemoteException
	 */
	public boolean addUser(User u) throws RemoteException;

	/**
	 * thong ke user co 3 luot binh luan tro len
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public Map<User, Integer> getStatistics() throws RemoteException;

	/**
	 * tim chinh xac theo email hoac ten
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public List<User> listUsers(String keyword) throws RemoteException;
}
