package cat.udl.eps.softarch.webglossary.utils;



import cat.udl.eps.softarch.webglossary.model.Alert;
import com.sun.swing.internal.plaf.metal.resources.metal;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
//    import net.rhizomik.mymusic.content.Song;

/**
 * Created by carlos on 5/15/14.
 */

public class XQueryHelper {
    private static final Logger log = Logger.getLogger(XQueryHelper.class.getName());
    private static final String namespaces = "cite=\"http://www.opengeospatial.net/cite\";";
    private Properties serializationProps;
    private XQPreparedExpression expr;
    private XQConnection conn;

    static final String albumsXQ =
            " declare namespace mmd=\"http://musicbrainz.org/ns/mmd-2.0#\"; " +
                    " declare variable $doc external; " +
                    " for $rec in $doc//mmd:recording " +
                    " let $years-from-date:=$rec//mmd:date[matches(text(),\"\\d{4}-\\d{2}-\\d{2}\")]/year-from-date(text()) " +
                    " let $years:=$rec//mmd:date[matches(text(),\"^\\d{4}$\")] " +
                    " return <recording> " +
                    "    {$rec/mmd:title/text()}, " +
                    "    {$rec/mmd:artist-credit//mmd:artist/mmd:name/text()}, " +
                    "    {distinct-values($rec//mmd:country)}, " +
                    "    {min(($years,$years-from-date))} " +
                    " </recording>";

    final URL inputURL = new URL("http://gencat.cat/transit/opendata/incidenciesGML.xml");

    static final String songsXQ =
            " declare namespace cite=\"http://www.opengeospatial.net/cite\"; "+
                "declare variable $doc := doc(\"http://www.gencat.cat/transit/opendata/incidenciesGML.xml\");"+
                " for $r in $doc//cite:mct2_v_afectacions_data "+
                " return <alert>" +
                    "{$r//cite:identificador/text()}," +
                    "{$r//cite:carretera/text()}," +
                    "{$r//cite:pk_inici/text()}," +
                    "{$r//cite:pk_fi/text()}," +
                    "{$r//cite:causa/text()}," +
                    "{$r//cite:cap_a/text()}," +
                    "{$r//cite:data/text()}," +
                    "{$r//cite:sentit/text()}," +
                    "{$r//cite:descripcio/text()}," +
                    "{$r//cite:desripcio_tipus/text()}" +
                "</alert>";

    XQueryHelper(String xquery, URL input)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XQException, IOException {
        URLConnection urlconn = input.openConnection();
        urlconn.setReadTimeout(50000);

        XQDataSource xqds = (XQDataSource)Class.forName("net.sf.saxon.xqj.SaxonXQDataSource").newInstance();
        this.conn = xqds.getConnection();
        this.expr = conn.prepareExpression(xquery);
        this.expr.bindDocument(new javax.xml.namespace.QName("doc"), urlconn.getInputStream(), null, null);
    }

    public XQueryHelper()
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XQException, IOException {
        URLConnection urlconn = inputURL.openConnection();
        urlconn.setReadTimeout(50000);

        XQDataSource xqds = (XQDataSource)Class.forName("net.sf.saxon.xqj.SaxonXQDataSource").newInstance();
        this.conn = xqds.getConnection();
        this.expr = conn.prepareExpression(songsXQ);
        //this.expr.bindDocument(new javax.xml.namespace.QName("doc"), urlconn.getInputStream(), null, null);
    }

    public ArrayList<Alert> getGencatAlerts() throws XQException {
        ArrayList<Alert> alerts = new ArrayList();
        XQResultSequence rs = this.expr.executeQuery();

        while (rs.next()) {
            XQItem item = rs.getItem();
            String s = item.getItemAsString(null);
            s = s.substring("<alert>".length(), s.length() - "</alert>".length());
            String result[] = s.split(",");

            String idString = result[0].trim();
            String road = result[1].trim();
            String startString = result[2].trim();
            String endString = result[3].trim();
            String cause = result[4].trim();
            String towards = result[5].trim();
            String date = result[6].trim();
            String direction = result[7].trim();
            String description = result[8].trim();
            String description_type = result[9].trim();

            double start = 0.0;
            try { start = Double.parseDouble(startString); }
            catch (Exception e) {}

            double end = 0.0;
            try { end = Double.parseDouble(endString); }
            catch (Exception e) {}

            Integer id = 0;
            try { id = Integer.parseInt(idString); }
            catch (Exception e) {}

             alerts.add(new Alert(id, road, start, end, cause, towards,date , direction, description, description_type));
        }
        rs.close();
        this.close();

        return alerts;
    }

    private void close() throws XQException {
        this.expr.close();
        this.conn.close();
    }
}