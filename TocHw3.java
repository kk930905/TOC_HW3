/**
 * name: 謝明翰
 * student number: F74001056
 * description: 挑出網頁中符合參數條件的JSON資料，並取平均。
 */
import java.net.*;
import java.io.*;
import org.json.*;

public class TocHw3 {
	public static void main(String[] args) throws JSONException {
		String input_township = "", input_road = "", input_year = "";
		String json_township = "", json_road = "", json_year = "";
		int avg_price, total_price = 0, amount = 0;
		
		try 
		{
			URL url = new URL(args[0]);
			URLConnection connect = url.openConnection();
			InputStreamReader stream = new InputStreamReader(connect.getInputStream(), "UTF-8");
			JSONArray json_data = new JSONArray(new JSONTokener(stream));
			
			input_township = args[1];
			input_road = args[2];
			
			for(int index = 0 ; index < json_data.length() ; index++)
			{
				json_township = json_data.getJSONObject(index).getString("鄉鎮市區");
				json_road = json_data.getJSONObject(index).getString("土地區段位置或建物區門牌");
				json_year = Integer.toString(json_data.getJSONObject(index).getInt("交易年月") / 100);

				for(int year = Integer.parseInt(args[3]) ; year <= 103 ; year++)
				{
					input_year = Integer.toString(year);
					
					if(json_township.equals(input_township) && json_road.contains(input_road) && json_year.equals(input_year))
					{
						total_price += json_data.getJSONObject(index).getInt("總價元");
						amount++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		avg_price = total_price / amount;
		System.out.println(avg_price);
	}
}
