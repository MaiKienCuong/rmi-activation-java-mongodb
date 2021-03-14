package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.News;

public interface News_interface extends Remote {

	/**
	 * get by id
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public News getByID(Long id) throws RemoteException;

	/**
	 * textsearch tren cot tags va Categories
	 * 
	 * @param value
	 * @return
	 * @throws RemoteException
	 */
	public List<News> getNewsByTagsOrCategoriesName(String value) throws RemoteException;

	/**
	 * tim news theo email cua user
	 * 
	 * @param email
	 * @return
	 * @throws RemoteException
	 */
	public List<News> listNewsByUserEmail(String email) throws RemoteException;

}
