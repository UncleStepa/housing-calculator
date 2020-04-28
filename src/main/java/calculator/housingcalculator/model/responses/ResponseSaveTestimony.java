package calculator.housingcalculator.model.responses;

import com.google.gson.annotations.SerializedName;







public class ResponseSaveTestimony{


	@SerializedName("date")
	private String date;


	@SerializedName("previousDate")
	private String previousDate;

	@SerializedName("consumed")
	private Consumed consumed;

	@SerializedName("cost")
	private Cost cost;

	@SerializedName("totalCost")
	private double totalCost;


	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setConsumed(Consumed consumed){
		this.consumed = consumed;
	}

	public Consumed getConsumed(){
		return consumed;
	}

	public void setCost(Cost cost){
		this.cost = cost;
	}

	public Cost getCost(){
		return cost;
	}

	public void setPreviousDate(String previousDate){
		this.previousDate = previousDate;
	}

	public String getPreviousDate(){
		return previousDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "ResponseSaveTestimony{" +
						"date='" + date + '\'' +
						", consumed=" + consumed +
						", cost=" + cost +
						", previousDate='" + previousDate + '\'' +
						", totalCost=" + totalCost +
						'}';
	}
}