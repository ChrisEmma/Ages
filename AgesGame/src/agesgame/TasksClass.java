package agesgame;

public class TasksClass {
	private String task;
	private int building;
	private int object;
	private int count;
	
	TasksClass(String task){
		this.task = task;
	}
	TasksClass(String task, int b){
		this.task = task;
		building = b;
	}
	TasksClass(String task, int b, int id){
		this.task = task;
		building = b;
		object = id;
	}
	TasksClass(String task, int b, int id, int c){
		this.task = task;
		building = b;
		object = id;
		count = c;
	}

	public String getString(){
		return task;
	}
	public int getBuilding(){
		return building;
	}
	public int getObject(){
		return object;
	}
	public int getCount(){
		return count;
	}
	public void setBuilding(int i){
		building = i;
	}
}
