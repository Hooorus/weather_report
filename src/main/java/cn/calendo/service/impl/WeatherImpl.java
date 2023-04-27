package cn.calendo.service.impl;

import cn.calendo.service.Weather;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

@Service
public class WeatherImpl implements Weather {

    @Override
    public ArrayList<String> weather() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            //发送get请求获取此api的json数据并转为string
            URL url = new URL("https://restapi.amap.com/v3/weather/weatherInfo?city=330108&key=32cff11e594e1542f5f5ffdadc27e129");
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("Connection", "keep-alive");
            conn.addRequestProperty("Accept", "*/*");
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64)");
            conn.connect();
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";
            while ((line = input.readLine()) != null) {
                result += line;
            }

            //一级读取，把string转为json格式，去除头部无效信息
            JSONObject jsonObject = JSON.parseObject(result);
            //list为内嵌数组,为去除首尾[]后的内容，供二级读取使用
            String listNoLast = jsonObject.getString("lives");
            String list = listNoLast.substring(1, listNoLast.length() - 1);

            //二级读取，读取各个键值对
            JSONObject listObj = JSON.parseObject(list);
            String province = listObj.getString("province");
            String city = listObj.getString("city");
            String adcode = listObj.getString("adcode");
            String windpower = listObj.getString("windpower");
            String winddirection = listObj.getString("winddirection");
            String weather = listObj.getString("weather");
            String temperature = listObj.getString("temperature");
            String humidity = listObj.getString("humidity");
            String reporttime = listObj.getString("reporttime");

            //装进去
            arrayList.add("地区: " + province + "\n");
            arrayList.add("城市: " + city + "\n");
            arrayList.add("城市编码: " + adcode + "\n");
            arrayList.add("风力: " + windpower + "\n");
            arrayList.add("风向: " + winddirection + "\n");
            arrayList.add("天气: " + weather + "\n");
            arrayList.add("温度: " + temperature + "°C" + "\n");
            arrayList.add("湿度: " + humidity + "%" + "\n");
            arrayList.add("时间: " + reporttime + "\n");

            //将这些变量输出
            System.out.println("地区: " + province + " " + "城市: " + city + " " + "城市编码: " + adcode + " "
                    + "风力: " + windpower + " " + "风向: " + winddirection + " " + "天气: " + weather + " "
                    + "温度: " + temperature + "°C" + " " + "湿度: " + humidity + "%" + " " + "时间: " + reporttime + " ");
            //URL与IO异常
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回字符串数组
        return arrayList;
    }
}
