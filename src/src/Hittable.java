package GuiEngine;
/**
 * hittable is an interface which is implemented by hittable objects of game: i.e. the wall and block.
 * @author fahadnayyar
 *
 */
public interface Hittable {
	/**
	 * hit method.
	 * @param s
	 */
	public void hit(Snake s);
	/**
	 * gives y coordinate.
	 * @return
	 */
	public double GetTranslateY();
	/**
	 * gives x coordinate.
	 * @return
	 */
	public double GetTranslateX();
	/**
	 * removes the hittable object from scene graph.
	 */
	public void RemoveFromScene();
	/**
	 * return value of blocks.
	 * @return
	 */
	public int getValue();
}
