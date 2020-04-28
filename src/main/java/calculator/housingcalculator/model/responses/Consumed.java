package calculator.housingcalculator.model.responses;


import com.google.gson.annotations.SerializedName;




public class Consumed{


	@SerializedName("coldWater")
	private int coldWater;

	@SerializedName("hotWater")
	private int hotWater;

	@SerializedName("gas")
	private int gas;

	@SerializedName("electricity")
	private int electricity;




	public void setColdWater(int coldWater){
		this.coldWater = coldWater;
	}

	public int getColdWater(){
		return coldWater;
	}

	public void setGas(int gas){
		this.gas = gas;
	}

	public int getGas(){
		return gas;
	}

	public void setElectricity(int electricity){
		this.electricity = electricity;
	}

	public int getElectricity(){
		return electricity;
	}

	public void setHotWater(int hotWater){
		this.hotWater = hotWater;
	}

	public int getHotWater(){
		return hotWater;
	}

	@Override
 	public String toString(){
		return 
			"Consumed{" + 
			"coldWater = '" + coldWater + '\'' +
			",hotWater = '" + hotWater + '\'' +
			",gas = '" + gas + '\'' +
			",electricity = '" + electricity + '\'' +
			"}";
		}
}