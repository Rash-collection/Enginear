/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package msgz;

import elmath.Ranged;
import entities.Bounded;
import entities.Exhaustable;
import funks.Updater;
import funks.Viewable;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

/**
 * for now messages class is not generic till there's a reason to.
 * @author rash4
 */
public class Message extends Bounded<Message> implements Viewable, Updater, Exhaustable {
    protected final static BufferedImage TEMPO = new BufferedImage(1,1,2);
    protected final static Color 
            OUT_LINE = new Color( 33,  88, 122, 255),
            INN_LINE = new Color(  0, 255, 200, 255),
            FOR_LINE = new Color(235, 113,  50, 255);
    /**minimal constructor for subclasses if there's any.*/
    protected Message(String msg, int tail){
        super();
        if(msg == null || msg.isEmpty()) // not restricting the blanks.
            throw new IllegalArgumentException("final 'msg' is null or empty.");
        this.timeLeft = means.Launcher.ups * 6; // six seconds
        this.MSG = msg;
        this.tailLength = tail;
        this.CANVA = paintCANVA(4, 6);
        super.boundary.setExtent(this.CANVA.getWidth(), this.CANVA.getHeight());
    }
    @Deprecated  @annots.Note("not ready at all")
    public Message(String msg, int tail, int x, int y){
        this(msg, tail);
        // needs the center and the radius, other calcs are done here..
        this.boundary.setCorner(x, y - tail);//not really yet..
    }
    /**The current global constructor.*/
    public Message(String msg, int tail, Ranged bounds){
        this(msg, tail);
        final var top = bounds.getRadius();
        this.boundary.setCenter(bounds.getCenter().subY(top).subY(this.boundary.getRadius()));
        this.timeFade = this.timeLeft * 0.4F;
    }
    @Override public void update(){
        this.timeLeft--;
    }
    @Override public boolean consumed(){return this.timeLeft <= 0;}
    /**overrides Viewable overloaded(unimplemented) render method.*/
    @Override public void render(Graphics2D grr, Rectangle range, Point delta, Rectangle cull) {
        if(!cull.contains(this.boundary.getCenter().point()))return;
        final var bnd = this.boundary.rectangle();
        final int xx = bnd.x - delta.x, yy = bnd.y - delta.y;
        final var comp = grr.getComposite();
        final boolean rest = this.timeFade > this.timeLeft;
        if(rest) grr.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                    this.consumed()? 0:this.timeLeft/this.timeFade));
        grr.drawImage(this.CANVA, xx, yy, bnd.width, bnd.height, null);
        grr.setComposite(comp);
    }
    protected final BufferedImage paintCANVA(int pad, int mrg){
        if(pad < 0 || mrg < 0) throw new IllegalArgumentException(
                "padding 'pad' or margin 'mrg' must not be niggative.");
        int w, h;
        Graphics2D g2 = TEMPO.createGraphics();
        g2.setFont(means.Launcher.font());
        var fm = g2.getFontMetrics();
        final int dbp = 2*pad, dbm = 2*mrg;
        w = fm.stringWidth(MSG) + dbm + dbp;  // +4 for some padding
        h = fm.getHeight() + dbm + dbp;
        // height = ascent + descent + leading
        final int lng = h + this.tailLength, wid = this.tailLength/3;
        final int arc = (int)Math.min(w, h)/3;
        g2.dispose();
        final var msg = new BufferedImage(w, lng, 2);
        final var grr = msg.createGraphics();
        grr.setFont(means.Launcher.font());
        
        grr.setColor(OUT_LINE);
        grr.fillRoundRect(0, 0, w, h, arc*2, arc*2);
        grr.setColor(INN_LINE);
        grr.fillRoundRect(mrg, mrg, w - dbm, h - dbm, arc, arc);
        
        final var ang = new Path2D.Float();
        ang.moveTo(w/2 - dbm, h - mrg);
        ang.lineTo((w/2 + dbm) + wid, h - mrg);
        ang.lineTo(w/2, lng);
        Area mrk = new Area(ang);
        grr.setColor(OUT_LINE);
        grr.fill(mrk);
        final var nag = new Path2D.Float();
        nag.moveTo(w/2 - mrg, h - dbm);
        nag.lineTo(w/2 + wid + mrg, h - dbm);
        nag.lineTo(w/2, lng - dbm);
        mrk = new Area(nag);
        grr.setColor(INN_LINE);
        grr.fill(mrk);
        
        grr.setColor(FOR_LINE);
        grr.drawString(this.MSG, mrg + pad, h - (mrg + pad + fm.getLeading()));
        grr.dispose();
        return msg;
    }
    public Message setTime(int t){
        this.timeLeft = Math.abs(t);
        this.timeFade = this.timeLeft * 0.4F;
        return this;
    }
    protected int timeLeft;
    protected float timeFade;
    protected final int tailLength;
    private final String MSG;
    private final BufferedImage CANVA;
}