package bookstorewebapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bookstorewebapp.domain.Book;
import bookstorewebapp.utils.HibernateUtil;


@WebServlet("/ReadBooksServlet")
public class ReadBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//Hibernate Query Language
		List <Book> books = session.createQuery("from Book").list();
		List <Book> javabooks = session.createQuery("from Book B where B.name='java'").list();
		
		
		//Criteria based queries -join multiple tables
		Criteria cr = session.createCriteria(Book.class);
		cr.add(Restrictions.eqOrIsNull("java", "java"));
		List<Book> books2 = cr.list();
		
		request.setAttribute("books", books2);
		
		//RequestDispatcher
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
}
