package model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import util.JpaUtil;

public class DaoGenericoImp<T, ID extends Serializable> implements DaoGenerico<T, ID> {


	private javax.persistence.EntityManager entityManager;

	private final Class<T> oClass;//object class
	private static final javax.persistence.EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public Class<T> getObjectClass() {
		return this.oClass;
	}


	@PersistenceContext(unitName="tudofacil")
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	protected EntityManager getEntityManager() {
		if (entityManager == null || !(entityManager.isOpen())) {
			//System.out.println("vai criar!");
			entityManager=emf.createEntityManager();
		}
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public DaoGenericoImp() {
		this.oClass = (Class<T>)
				( (ParameterizedType) getClass().getGenericSuperclass() ).
				getActualTypeArguments()[0];

	}

	public T atualizar(T object) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		object = em.merge(object);
		em.getTransaction().commit();
		//em.flush();
		em.close();
		return object;

	}

	public void excluir(T object) {
		EntityManager em = getEntityManager();
		object = em.merge(object);
		em.remove(object);
		em.flush();
		em.close();
	}


	public T pesquisarPorId(ID id) {
		EntityManager em = getEntityManager();
		T retorno = em.find(oClass, id);
		em.close();
		return retorno;
	}

	public T salvar(T object) {
		EntityManager em;
		em = getEntityManager();
		em.getTransaction().begin();
		object = em.merge(object);
		em.getTransaction().commit();
		//em.flush();
		em.close();
		return object;
	}

	@SuppressWarnings("unchecked")
	public List<T> todos(){
		EntityManager em = getEntityManager();
		String queryS = "SELECT obj FROM "+oClass.getSimpleName()+" obj";
		Query query = em.createQuery(queryS);
		List<T> retorno = query.getResultList();
		em.close();
		return retorno;

	}

	@SuppressWarnings("unchecked")
	public List<T> listPesqParam(String query, Map<String, Object> params){
		EntityManager em = getEntityManager();
		Query q = em.createQuery(query);
		for(String chave : params.keySet()){
			q.setParameter(chave, params.get(chave));

		}
		List<T> retorno = q.getResultList();
		em.close();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<T> listPesqParam(String query, Map<String, Object> params,
			int maximo, int atual){
		
		EntityManager em = getEntityManager();
		Query q = em.
				createQuery(query).
				setMaxResults(maximo).
				setFirstResult(atual);
		for(String chave : params.keySet()){
			q.setParameter(chave, params.get(chave));

		}
		List<T> retorno = q.getResultList();
		em.close();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<T> listPesq(String query){
		EntityManager em = getEntityManager();
		Query q = em.createQuery(query);
		List<T> retorno = q.getResultList();
		em.close();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public T pesqParam(String query, Map<String, Object> params){
		EntityManager em = getEntityManager();
		Query q = em.createQuery(query);
		for(String chave : params.keySet()){
			q.setParameter(chave, params.get(chave));
		}
		T retorno;
		try{
			retorno = (T) q.getSingleResult();
		}catch(NoResultException nre){
			retorno = null;
		}
		em.close();
		return retorno;
	}
}
