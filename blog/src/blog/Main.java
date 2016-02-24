package blog;

public class Main {

	public static void main(String[] args) {
		Downloader downloader = new Downloader("http://pastyy.blogspot.com/sitemap.xml");
		
		Plagiaty plagiaty = new Plagiaty(downloader.getPostsContent());
		plagiaty.check();
	}
}
