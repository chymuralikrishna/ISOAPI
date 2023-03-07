package com.nfc.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.nfc.main.objects.SelectItem;
import com.nfc.util.HibernateUtil;

/**
 *
 * @author NFC
 */
public class HibernateQueryISO {

	static Logger logger = Logger.getLogger(HibernateQueryISO.class);

	@SuppressWarnings("unchecked")
	public List<Object[]> getQueryResultFromSession(String Query) {
		Session session = null;
		List<Object[]> objList = new ArrayList<>();
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			objList = session.createSQLQuery(Query).list();

			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return objList;
	}

	public List<Object> getObjectListFromSession(String Query) {
		Session session = null;
		List<Object> objList = new ArrayList<>();
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			objList = session.createSQLQuery(Query).list();

			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return objList;
	}

	public Integer getIntegerValue(String query) {
		Session session = null;
		Integer count = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			count = (Integer) session.createSQLQuery(query).uniqueResult();

			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return count;
	}

	public BigDecimal getBigDecimalValue(String query) {
		Session session = null;
		BigDecimal count = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			count = (BigDecimal) session.createSQLQuery(query).uniqueResult();

			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return count;
	}

	public Boolean getBooleanValue(String query) {
		Session session = null;
		Boolean result = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			result = (Boolean) session.createSQLQuery(query).uniqueResult();

			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return result;
	}

	public String getStringFromSession(String query) {
		Session session = null;
		String result = " ";
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			result = (String) session.createSQLQuery(query).uniqueResult();

			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return result;
	}

	public Date getDateValue(String query) {
		Session session = null;
		Date date = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			date = (Date) session.createSQLQuery(query).uniqueResult();

			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return date;
	}

	public List createQueryAndGetList(String query) {
		Session session = null;
		List listmast = new ArrayList();
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			listmast = session.createQuery(query).list();

		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if (session != null) {
				session.clear();
				session.flush();
				session.close();
			}
		}
		return listmast;
	}

	public Integer saveDetailsToFusionTable(Object obj) {
		Integer result = 0;
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();
			session.save(obj);
			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			if (session != null) {
				session.clear();
				session.flush();
				session.close();
			}
		}
		return result;
	}

	public Integer saveOrUpdateDetailsToFusionTable(Object obj) {
		Integer result = 0;
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(obj);
			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			session.flush();
			session.close();
		}
		return result;
	}

	public List<String> getListOfStringFromFusion(String query) {
		Session session = null;
		List<String> list = new ArrayList<>();
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			list = session.createSQLQuery(query).list();

		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if (session != null) {
				session.clear();
				session.flush();
				session.close();
			}
		}

		return list;

	}

	public List<Date> getListOfDatesFromFusion(String query) {
		Session session = null;
		List<Date> list = new ArrayList<>();
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			list = session.createSQLQuery(query).list();
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if (session != null) {
				session.clear();
				session.flush();
				session.close();
			}
		}

		return list;

	}

	public Integer saveListOfobjectsToFusionTable(List objList) {
		Integer result = 0;

		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();

			for (Object obj : objList) {
				session.saveOrUpdate(obj);
			}

			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			// session.flush();
			session.close();
		}

		return result;
	}

	public Integer saveOrUpdateListOfobjectsToFusionTable(List objList) {
		Integer result = 0;

		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();

			for (Object obj : objList) {
				session.saveOrUpdate(obj);
			}

			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			// session.flush();
			session.close();
		}

		return result;
	}

	public Integer deleteExistingAndSaveListOfObjectsToFusionTable(String tableName, String columnName,
			String columnValue, List objList) {
		Integer result = 0;

		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();

			String query = " delete from " + tableName + " where " + columnName + " = '" + columnValue + "'";
			session.createSQLQuery(query).executeUpdate();
			for (Object obj : objList) {
				session.save(obj);
			}

			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			session.flush();
			session.close();
		}

		return result;
	}

	public Integer deleteExistingAndSaveListOfObjectsToFusionTable(String tableName, String columnName, int columnValue,
			List objList) {
		Integer result = 0;

		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();

			String query = " delete from " + tableName + " where " + columnName + " = " + columnValue;
			session.createSQLQuery(query).executeUpdate();
			for (Object obj : objList) {
				session.save(obj);
			}

			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			session.flush();
			session.close();
		}

		return result;
	}

	public Integer executeQueryAndSaveListOfObjectsToFusionTable(List objList, String updateQuery) {
		Integer result = 0;

		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();
			session.createSQLQuery(updateQuery).executeUpdate();
			for (Object obj : objList) {
				session.saveOrUpdate(obj);
			}

			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			session.flush();
			session.close();
		}

		return result;
	}

	public Integer deleteExistingAndSaveListOfObjectsToFusionTable(String tableName, String deleteCondition,
			List objList) {
		Integer result = 0;

		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();

			String query = " delete from " + tableName + " where " + deleteCondition;
			session.createSQLQuery(query).executeUpdate();
			for (Object obj : objList) {
				session.save(obj);
			}

			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			session.flush();
			session.close();
		}

		return result;
	}

	public Integer deleteObjectFromFusionTable(String tableName, String columnName, String columnValue) {
		Integer result = 0;

		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();

			String query = " delete from " + tableName + " where " + columnName + " = '" + columnValue + "'";
			session.createSQLQuery(query).executeUpdate();

			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			session.flush();
			session.close();
		}

		return result;
	}

	public Integer executeUpdateQueryOnTable(String query) {
		Integer result = 0;

		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			tx = session.beginTransaction();
			session.createSQLQuery(query).executeUpdate();
			result = 1;
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);
			result = 0;
		} finally {
			session.flush();
			session.close();
		}

		return result;
	}

	public HashMap<String, String> getHashMapFromSession(String Query) {
		Session session = null;
		HashMap<String, String> hashMap = new HashMap();
		List<Object[]> objList;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			objList = session.createSQLQuery(Query).list();
			objList.forEach((obj) -> {
				hashMap.put((String) obj[0], (String) obj[1]);
			});
			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return hashMap;
	}

	public HashMap<Integer, String> getHashMapWithIntegerKeyFromSession(String Query) {
		Session session = null;
		HashMap<Integer, String> hashMap = new HashMap();
		List<Object[]> objList;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			objList = session.createSQLQuery(Query).list();
			objList.forEach((obj) -> {
				hashMap.put((Integer) obj[0], (String) obj[1]);
			});
			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return hashMap;
	}

	public HashMap<String, Integer> getHashMapWithIntegerValueFromSession(String Query) {
		Session session = null;
		HashMap<String, Integer> hashMap = new HashMap();
		List<Object[]> objList;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			objList = session.createSQLQuery(Query).list();
			objList.forEach((obj) -> {
				hashMap.put((String) obj[0], (Integer) obj[1]);
			});
			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return hashMap;
	}

	public BidiMap getBidiMapFromSession(String Query) {
		Session session = null;
		BidiMap hashMap = new TreeBidiMap();
		List<Object[]> objList;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();

			objList = session.createSQLQuery(Query).list();
			objList.forEach((obj) -> {
				hashMap.put((String) obj[0], (String) obj[1]);
			});

			// for (Object[] obj : objList) {
			// hashMap.put((String) obj[0], (String) obj[1]);
			// }
			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}
		return hashMap;
	}

	public List<Map<String, Object>> getJsonResult(String Query) {
		Session session = null;

		List<Map<String, Object>> list = null;
		try {
			session = HibernateUtil.getIsosessionfactory().openSession();
			list = session.createSQLQuery(Query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			session.flush();
			session.close();
			session = null;
		} catch (HibernateException e) {
			java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			if (session != null) {
				session.flush();
				session.close();
				session = null;
			}
		}

		return list;

	}
	
	public List getSelectItemListFromSession(String Query) {
        Session session = null;
        List<Object[]> objList;
        List<SelectItem> selectItemList = new ArrayList<>();
        try {
        	session = HibernateUtil.getIsosessionfactory().openSession();

            objList = session.createSQLQuery(Query).list();

            objList.forEach((obj) -> {
                selectItemList.add(new SelectItem((String) obj[0], (String) obj[1]));
            });

            session.flush();
            session.close();
            session = null;
        } catch (HibernateException e) {
            java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
        return selectItemList;
    }
	
	public List getSelectItemListWithIntegerValueFromSession(String Query) {
        Session session = null;
        List<Object[]> objList;
        List<SelectItem> selectItemList = new ArrayList<>();
        try {
        	session = HibernateUtil.getIsosessionfactory().openSession();

            objList = session.createSQLQuery(Query).list();

            objList.forEach((obj) -> {
                selectItemList.add(new SelectItem((String) obj[0], (Integer) obj[1]));
            });

            session.flush();
            session.close();
            session = null;
        } catch (HibernateException e) {
            java.util.logging.Logger.getLogger(HibernateQueryISO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
        return selectItemList;
    }

}
