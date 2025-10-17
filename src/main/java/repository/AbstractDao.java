package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractDao<T> {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hr");

    private final Class<T> entityClass;

    protected AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public T find(Object id) {
        return executeInTransactionWithResult(tx -> getEntityManager().find(entityClass, id));
    }

    public List<T> findAll() {
        return executeInTransactionWithResult(tx -> getEntityManager()
                .createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList());
    }

    public void persist(T entity) {
        executeInTransaction(tx -> getEntityManager().persist(entity));
    }

    public T merge(T entity) {
        return executeInTransactionWithResult(tx -> getEntityManager().merge(entity));
    }

    public void remove(T entity) {
        executeInTransaction(tx -> getEntityManager().remove(entity));
    }

    public void flush() {
        executeInTransaction(EntityManager::flush);
    }

    private void executeInTransaction(Consumer<EntityManager> action) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    private <R> R executeInTransactionWithResult(Function<EntityManager, R> action) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            R result = action.apply(em);
            tx.commit();
            return result;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }


}
