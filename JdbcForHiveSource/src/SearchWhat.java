import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class SearchWhat {
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader("/home/cloudera/workspace/AmazonAnalysis/cache/reviews.csv"));
		
		BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in));
		
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		
		System.out.println("Enter your Login ID: ");
		String loginId = readInput.readLine();
		Show_bought_together sbt = new Show_bought_together();
		String reviewerid="";
		String[] boughtProduct = new String[11];
		String line="";
		
		
		int productCount=0;
		while((line=br.readLine())!=null)
		{
			String[] reviewDetail = line.trim().split(",");
			
			if(reviewDetail[1].equalsIgnoreCase(loginId))
			{
				reviewerid = reviewDetail[0];
				boughtProduct[productCount]=reviewDetail[2];
				productCount++;
				if(productCount>10)
					break;
			}
		}

		
		ArrayList<String> showThese = sbt.getProductsBoughtByUser(reviewerid,boughtProduct);
		
		Iterator<String> i = showThese.iterator();
		ArrayList<String> displayThese = new ArrayList<String>();
		System.out.println("Hi "+loginId+" you may want to check these....");
		while(i.hasNext())
		{
			try{
				String productline="";
				BufferedReader readProduct = new BufferedReader(new FileReader("/home/cloudera/workspace/AmazonAnalysis/cache/id_name.csv"));
				String tod = i.next();
			//System.out.println("this is i.next: "+tod);
			while((productline=readProduct.readLine())!=null)
			{
				String[] displayProduct = productline.trim().split(",");
				if(displayProduct[0].equalsIgnoreCase(tod))
				{
					//System.out.println(displayProduct[0]);
					if(!displayThese.contains(displayProduct[1]))
						displayThese.add(displayProduct[1]);
					//System.out.println();
				}
				
			}
			readProduct.close();
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Looks like this product is not in Sports category");
			}
		}
		
		for (String s : displayThese)
		{
			System.out.println(s);
		}
		
		br.close();
		System.out.println("End");
		
	}

}
