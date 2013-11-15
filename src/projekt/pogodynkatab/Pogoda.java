package projekt.pogodynkatab;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class Pogoda extends Activity {

	public ImageView ikonka;
	public TextView lokacja;
	public TextView dzisiaj;
	public ForecastDay dzien;
	public TextView pogoda1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pogoda);
		if (ForecastActivity._mainActivity != null) {
			ikonka = (ImageView) findViewById(R.id.ikonkaIV);
			URL url;
			Bitmap bmp;
			try {
				url = new URL(ForecastActivity._mainActivity.cndtns.iconURL);
				bmp = BitmapFactory.decodeStream(url.openConnection()
						.getInputStream());
				ikonka.setImageBitmap(bmp);
			} catch (MalformedURLException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			} catch (IOException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			lokacja = (TextView) findViewById(R.id.lokacja2TB);
			dzisiaj = (TextView) this.findViewById(R.id.data);
			try {
				Log.i("POGODA", ForecastActivity._mainActivity.disLoc.getCity());
				lokacja.setText(ForecastActivity._mainActivity.disLoc.getCity());
				dzien = ForecastActivity._mainActivity.simpleForecast.get(0);
				dzisiaj.setText("Dzisiaj jest " + dzien.data.weekDay + ", "
						+ dzien.data.day + " " + dzien.data.monthName + " "
						+ dzien.data.year + "\n");
				pogoda1 = (TextView) this.findViewById(R.id.pogoda1);
				String cisnTrend = "";
				if (ForecastActivity._mainActivity.cndtns.pressureTrend
						.equals("-")) {
					cisnTrend = "spada";
				} else if (ForecastActivity._mainActivity.cndtns.pressureTrend
						.equals("+")) {
					cisnTrend = "ro�nie";
				} else {
					cisnTrend = "stale / brak danych";
					Log.i("CI�NIENIE",
							ForecastActivity._mainActivity.cndtns.pressureTrend);
				}

				String pogoda = ForecastActivity._mainActivity.cndtns.weather
						+ "\nTemp: "
						+ ForecastActivity._mainActivity.cndtns.tempC
						+ "C 	Odczuwalna:"
						+ ForecastActivity._mainActivity.cndtns.feelslikeC
						+ "C\nWiatr "
						+ ForecastActivity._mainActivity.cndtns.windDir
						+ ", "
						+ ForecastActivity._mainActivity.cndtns.windKph
						+ "km/h"
						+ "\nOpady "
						+ ForecastActivity._mainActivity.current_observation
								.getString("precip_today_metric")
						+ "\nCi�nienie 	"
						+ ForecastActivity._mainActivity.cndtns.pressureMb
						+ "hPa, "
						+ cisnTrend
						+ "\nWidoczno�� "
						+ ForecastActivity._mainActivity.cndtns.visibilityKm
						+ "km"
						+ "\nWilgotno�� "
						+ ForecastActivity._mainActivity.cndtns.relativeHumidity;
				pogoda1.setText(pogoda);
			} catch (Exception ex) {
				Log.i(ex.getMessage(), ex.toString());
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pogoda, menu);
		return true;
	}

}
