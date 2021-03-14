package ui;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.News_interface;
import dao.User_interface;
import entity.Address;
import entity.Comment;
import entity.Gender;
import entity.User;

public class Client {

	public static void main(String[] args) {
		SecurityManager sm = System.getSecurityManager();
		if (sm == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {

			User_interface user_dao = (User_interface) Naming.lookup("rmi://DESKTOP-3FJ9IE1:9999/userdao");
			News_interface news_dao = (News_interface) Naming.lookup("rmi://DESKTOP-3FJ9IE1:9999/newsdao");

			/*
			 * cau 1
			 */
			System.out.println();
			System.out.println("==========================================================");
			System.out.println("=================cau1=================");
			System.out.println();
			Set<String> researchAreas = new HashSet<String>();
			researchAreas.add("cxXZ");
			researchAreas.add("gjhgj");
			Address userAddress = new Address("dqh", "ho chi minh", "go vap", "12345");
			User user = new User(5l, "kien", "cuong@gmail.com", "123", researchAreas, userAddress, Gender.FEMALE);
			List<Comment> listOfComments = new ArrayList<Comment>();
			listOfComments.add(new Comment("dsfds", LocalDateTime.now(), news_dao.getByID(20l)));
			listOfComments.add(new Comment("yuikjhm", LocalDateTime.now(), news_dao.getByID(21l)));
			listOfComments.add(new Comment("uymn", LocalDateTime.now(), news_dao.getByID(19l)));
			listOfComments.add(new Comment("mhjmj", LocalDateTime.now(), news_dao.getByID(19l)));
			user.setListOfComments(listOfComments);

			boolean b = user_dao.addUser(user);
			if (b) {
				System.out.println("them thanh cong user");
			} else {
				System.out.println("them user khong thanh cong");
			}

			/*
			 * cau 2, phai tao text index tren 2 field tags va newCategories truoc khi chay
			 */
//			db.news.createIndex({"tags": "text", "newsCategories": "text"})
			System.out.println();
			System.out.println("==========================================================");
			System.out.println("=================cau2=================");
			System.out.println();
			try {
				news_dao.getNewsByTagsOrCategoriesName("Analytic").subList(0, 5).forEach(x -> System.out.println(x));
			} catch (Exception e) {
			}

			/*
			 * cau 3
			 */
			System.out.println();
			System.out.println("==========================================================");
			System.out.println("=================cau3=================");
			System.out.println();
			user_dao.getStatistics().forEach((u, count) -> {
				System.out.println("nguoi dung " + u);
				System.out.println("so binh luan " + count);
				System.out.println("======================");
			});

			/*
			 * cau 4
			 */
			System.out.println();
			System.out.println("==========================================================");
			System.out.println("=================cau4=================");
			System.out.println();
			news_dao.listNewsByUserEmail("cuong@gmail.com").subList(0, 3).forEach(x -> System.out.println(x));

			/*
			 * cau 5
			 */
			System.out.println();
			System.out.println("==========================================================");
			System.out.println("=================cau5=================");
			System.out.println();
			user_dao.listUsers("cuong@gmail.com").forEach(x -> System.out.println(x));

		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
