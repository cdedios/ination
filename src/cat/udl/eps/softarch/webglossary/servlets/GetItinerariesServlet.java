package cat.udl.eps.softarch.webglossary.servlets;

import cat.udl.eps.softarch.webglossary.persistence.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by carlos on 5/18/14.
 */
public class GetItinerariesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.get().createEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            em.persist(ge);
            txn.commit(); }
        catch (Exception e) {
            log.warning(e.getMessage()); }
        finally {
            if (txn.isActive()) txn.rollback();
            em.close(); }
    }

}
