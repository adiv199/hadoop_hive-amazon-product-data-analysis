import java.util.ArrayList;
import java.util.Iterator;






public class Show_bought_together {
	
	public ArrayList<String> getProductsBoughtByUser(String r, String[] s)
	{
		
		
		HiveJdbcClient hjc = new HiveJdbcClient();
		//ReviewerBean rb = new ReviewerBean();
		ArrayList<ProductBean> arrayOfProducts = new ArrayList<ProductBean>();
		ArrayList<String> toShow = new ArrayList<String>();
		
		//rb.setReviewerid(r);
		
		for(int i=0;i<s.length;i++)
		{
			ProductBean pb = new ProductBean();
			pb.setAsin(s[i]);
			arrayOfProducts.add(pb);
			toShow.add(pb.getAsin());
			
		}
		
		
		
		Iterator<ProductBean> i = arrayOfProducts.iterator();
		
		while(i.hasNext())
		{
			
			ProductBean b = i.next();
			toShow.addAll(hjc.getProductBoughtTogether(b));
			toShow.addAll(hjc.getProductAlsoBoughtTogether(b));
			toShow.addAll(hjc.getProductViewedTogether(b));
		}
		
		return toShow;
	}

}
