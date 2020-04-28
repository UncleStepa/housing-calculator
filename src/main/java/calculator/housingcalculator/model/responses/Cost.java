package calculator.housingcalculator.model.responses;

import com.google.gson.annotations.SerializedName;

public class Cost{

	@SerializedName("coldWater")
	private double coldWater;

	@SerializedName("finalCost")
	private double finalCost;

	@SerializedName("gas")
	private double gas;

	@SerializedName("electricity")
	private double electricity;

	@SerializedName("hotWater")
	private double hotWater;

	public void setColdWater(double coldWater){
		this.coldWater = coldWater;
	}

	public double getColdWater(){
		return coldWater;
	}

	public void setFinalCost(double finalCost){
		this.finalCost = finalCost;
	}

	public double getFinalCost(){
		return finalCost;
	}

	public void setGas(double gas){
		this.gas = gas;
	}

	public double getGas(){
		return gas;
	}

	public void setElectricity(double electricity){
		this.electricity = electricity;
	}

	public double getElectricity(){
		return electricity;
	}

	public void setHotWater(double hotWater){
		this.hotWater = hotWater;
	}

	public double getHotWater(){
		return hotWater;
	}

	@Override
 	public String toString(){
		return 
			"Cost{" + 
			"coldWater = '" + coldWater + '\'' + 
			",finalCost = '" + finalCost + '\'' + 
			",gas = '" + gas + '\'' + 
			",electricity = '" + electricity + '\'' + 
			",hotWater = '" + hotWater + '\'' + 
			"}";
		}
}