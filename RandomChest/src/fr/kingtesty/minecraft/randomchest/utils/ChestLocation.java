package fr.kingtesty.forrun.randomchest.utils;

public class ChestLocation
{
	private String x;
	private String y;
	private String z;
	private String nombre;

	public ChestLocation(String x, String y, String z, String nombre)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.nombre = nombre;
	}
	public String getX() {
		return x;
	}
	public String getY() {
		return y;
	}
	public String getZ() {
		return z;
	}
	public String getNombre() {
		return nombre;
	}
}
