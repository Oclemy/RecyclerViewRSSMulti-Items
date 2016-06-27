package com.tutorials.hp.recyclerviewrssmulti_items.m_RSS;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tutorials.hp.recyclerviewrssmulti_items.m_DataObject.Article;
import com.tutorials.hp.recyclerviewrssmulti_items.m_UI.MyAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Oclemy on 6/11/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class RSSParser extends AsyncTask<Void,Void,Boolean> {


    Context c;
    InputStream is;
    RecyclerView rv;

    ProgressDialog pd;
    ArrayList<Article> articles=new ArrayList<>();

    public RSSParser(Context c, InputStream is, RecyclerView rv) {
        this.c = c;
        this.is = is;
        this.rv = rv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parse data");
        pd.setMessage("Parsing data...Please wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseRSS();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        pd.dismiss();
        if(isParsed)
        {
            //Bind
            rv.setAdapter(new MyAdapter(c,articles));

        }else {
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseRSS()
    {
        try
        {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=factory.newPullParser();

            parser.setInput(is, null);
            int event=parser.getEventType();

            String value=null;

            articles.clear();
            Article article=new Article();

            do {
                String name=parser.getName();

                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        if(name.equals("item"))
                        {
                            article=new Article();
                        }

                        break;

                    case XmlPullParser.TEXT:
                        value=parser.getText();
                        break;


                    case XmlPullParser.END_TAG:

                            if(name.equals("title"))
                            {
                                article.setTitle(value);

                            }
                            else if(name.equals("description"))
                            {
                               article.setDescription(value);

                            }else if(name.equals("pubDate"))
                            {
                                article.setDate(value);

                            }else if(name.equals("date"))
                            {
                               article.setDate(value);


                            }




                        if(name.equals("item"))
                        {
                            articles.add(article);
                        }
                        break;
                }


                event=parser.next();

            }while (event != XmlPullParser.END_DOCUMENT);

            return true;


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }















}
