import me.veir.excelconverter.Util.HibernateUtil;
import me.veir.excelconverter.model.Station;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Assert;
import org.junit.Test;

public class ModelTest {
    @Test
    public void testGetSession(){
        Session session =HibernateUtil.getSession();
        Assert.assertNotNull(session);
        HibernateUtil.closeSession();
    }

    @Test
    public void testSave(){
        Station station = new Station();
        /*station.setStation(100);
        station.setLongitudex(10.1);
        station.setLatitudey(20.3);*/

        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(station);
        tx.commit();
        HibernateUtil.closeSession();
    }

    @Test
    public void testQuery(){
        Session session = HibernateUtil.getSession();
        session.createQuery("from T_Station").list().forEach(x-> System.out.println(x));

        HibernateUtil.closeSession();
    }
}
