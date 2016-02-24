package blog;

import java.util.ArrayList;

public class Plagiaty {
	public ArrayList<Post> posts;
	
	Plagiaty(ArrayList<Post> posts) {
		this.posts = posts;
	}
	
	public void check() {
		for(int i = 0;  i < posts.size(); i++) {
			for(int j = 0;  j < posts.size(); j++) {
				if(i!=j) {
					//System.out.println(posts.get(i).title + " | " +posts.get(j).title);
					int same = 0;
					String[] words1 = posts.get(i).body.split(" ");
									
					for(int i1 = 0; i1 < words1.length; i1++) {
						//System.out.println(words1[i1]);
							if(posts.get(j).body.contains(words1[i1]))
								same++;
					}
					
					if((same/words1.length)*100 > 30)
						System.out.println((same/words1.length)*100 + " | " + posts.get(i).title + " | " +posts.get(j).title);
				}
			}
		}
	}
}
