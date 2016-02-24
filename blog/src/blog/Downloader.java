package blog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

public class Downloader {

	private String mapUrl;
	
	public Downloader(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public ArrayList<Post> getPostsContent() {
		ArrayList<Post> posts = new ArrayList<Post>();
		ArrayList<String> links = getPostsLinks();
		
		for(String l : links) {
			System.out.println(l);
			String html = getURLContent(l);

			String title = title(html);
			//System.out.println(title);
			
			String body = body(html);
			//System.out.println(body);
			
			posts.add(new Post(title, body, l));
		}
		
		return posts;
	}
	
	public String title(String post) {
		String title = "";
		
		Pattern p = Pattern.compile("<title>Pasty: [(%)A-Za-zøüÊÒÛ≥ÍπúØè∆•å £”— 0-9\\,\\-\\._\\?]+</title>");
		Matcher m = p.matcher(post);
		
		//System.out.println(post);
		while(m.find()) {
			String h3 = post.substring(m.start(), m.end());
			//System.out.println(h3);
			title = h3.split("<title>Pasty: ")[1].replace("</title>", "");
		}
		return title;
	}

	public String body(String post) {
		String body = "";
		body = StringUtils.substringBetween(post, " itemprop='description articleBody'>", "<div style='clear: both;'></div>");
		return Jsoup.parse(body).text();
	}
	
	public ArrayList<String> getPostsLinks() {
		String map = getURLContent(mapUrl);
		ArrayList<String> links = new ArrayList<String>();

		Pattern p = Pattern.compile("http://pastyy.blogspot.com/[0-9]+/[0-9]+/[a-zA-Z0-9_-]+.html");
		Matcher m = p.matcher(map);

		while(m.find()) {
			links.add(map.substring(m.start(), m.end()));
			System.out.println(map.substring(m.start(), m.end()));
		}
		
		return links;
	}

	public String getMap() {
		return getURLContent(mapUrl);
	}
	
	public String getURLContent(String p_sURL) {
		URL oURL;
		URLConnection oConnection;
		BufferedReader oReader;
		String sLine;
		StringBuilder sbResponse;
		String sResponse = null;

		try {
			oURL = new URL(p_sURL);
			oConnection = oURL.openConnection();
			oReader = new BufferedReader(new InputStreamReader(oConnection.getInputStream(), "UTF-8"));
			sbResponse = new StringBuilder();

			while ((sLine = oReader.readLine()) != null) {
				sbResponse.append(sLine);
			}

			sResponse = sbResponse.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sResponse;
	}
}
