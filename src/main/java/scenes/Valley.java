/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package scenes;

import elmath.Vector;
import engine.Lock;
import entities.ActiveTerrain;
import entities.Bounded;
import entities.Player;
import entities.Terrain;
import items.Apples;
import items.Rocks;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import means.Launcher;
import msgz.Message;

/**
 * has three views (earth/map/info!)
 * has four entities (player/background/others)
 * 
 * finish all multi-view's AUTO methods then make the MAIN scene fast for a first test run
 * @author rash4
 */
public class Valley extends MultiViews<Valley>{
    private final static int GC_INT = 120 * 10;
    private static int gcCount = 0;
    public Valley(String name){
        super(name);
        this.PLAYER = new Player();
        this.consumables = new ArrayList<>();
        this.lockCons = new Lock();
    }
    
    public Valley initValley(){
        final Color bk = new Color(160, 250, 0);
        final var ptr = this.self();
        final ViewableScene main = this.getMain();
        main.setInitializer(()->{
            this.PLAYER.setSprite(elfactory.Materializing.spirit());
            final var plr = this.PLAYER;
            final msgz.Message msge = new msgz.Message("something is going on!", 24, plr.boundary);
            main
                .setResizer(()->{
                    final var pns = Launcher.size();
                    final Point piv = main.getView().getPivot();
                    main.getView().getBounds()
                            .setExtent(pns.width - (2*piv.x), pns.height - (2*piv.y))
                            .setCorner(piv.x, piv.y);
                    this.centralize();
                })
                .setPreLoader(DEF_PRELOAD)
                .setPostLeave(DEF_POSTLEAVE)
                .setUpdates(()->{
                    synchronized(this.PLAYER.lock()){
                        this.PLAYER.update();
                        float dx = 0, dy = 0;
                        if (plr.nort()) dy -= 1;
                        if (plr.sout()) dy += 1;
                        if (plr.west()) dx -= 1;
                        if (plr.east()) dx += 1;
                        Vector movement = new Vector(dx, dy);
                        if (dx != 0 || dy != 0) {
                            final int speed = this.speeding? 11 : 5;
                            movement = movement.normalize().scale(speed);
                            final Bounded nextStep = new Bounded(plr.boundary.rectangle()){};
                            nextStep.boundary.setCenter(plr.boundary.getCenter().add(movement));
                            if(!outBounds(main.getEntities(), nextStep)){
                                plr.boundary.setCenter(plr.boundary.getCenter().add(movement));
                                if(this.keepCentered) this.centralize();
                            }
                            final var siz = plr.boundary.getExtent().dimension();
                            plr.target = plr.boundary.getCenter().add(siz.width * dx, siz.height * dy);
                        }
                    }
                    msge.update();
                    
//                    gcCount++;
//                    if(gcCount >= GC_INT){
//                        gcCount = 0;
//                        System.gc();
//                    }
                })
                .setViewable((grr, bnd, del, cull)->{
                    final var scrn = main.getView().screen();
                    final var ter = main.getEntities();
                    final var olc = grr.getClip();
                    grr.setClip(scrn);
                    grr.setColor(bk);
                    grr.fill(scrn);
                    synchronized(main.lock()){
                        for(var terrain : ter){
                            if(terrain instanceof Terrain trn)trn.render(grr, bnd, del, cull);
                        }
                    }
                    synchronized(this.lockCons){
                        for(var con : this.consumables){
                            con.render(grr, bnd, del, cull);
                        }
                    }
                    msge.render(grr, bnd, del, cull);
                    synchronized(this.PLAYER.lock()){
                        this.PLAYER.render(grr, bnd, del, cull);
                    }
                    if(this.pause != null) this.pause.render(grr, bnd, del, cull);
                    grr.setClip(olc);
                    grr.setColor(Color.BLACK);
                    grr.draw(scrn);
                })
            ;
            main.getView().setPivot(32, 32).setMarg(32, 32);
            this.mainKeys();
            return main;
        }).initialize();
        // the parent scene(container)
        return this.setInitializer(()->{
            this.createGrid(128, 128);
            ptr
                .setResizer(ptr::autoResizer)
                .setPreLoader(()->{
                    ptr.setTitle();
                    ptr.autoOnLoad(Launcher.GAME.contentPane());
                })
                .setPostLeave(()->{
                    ptr.autoOnLeave(Launcher.GAME.contentPane());
                })
                .setUpdates(()->{
                    ptr.autoUpdater();
                })
                .setRenders(grr->{
                    ptr.autoRender(grr);
                })
                .add();
            return ptr;
        }).initialize();
    }
    private void mainKeys(){
        this.getMain().setKeys(new KeyAdapter(){
            @Override public void keyPressed(KeyEvent ke){
                switch(ke.getKeyCode()){
                    case KeyEvent.VK_W-> Valley.this.PLAYER.setNort(true);
                    case KeyEvent.VK_A-> Valley.this.PLAYER.setWest(true);
                    case KeyEvent.VK_S-> Valley.this.PLAYER.setSout(true);
                    case KeyEvent.VK_D-> Valley.this.PLAYER.setEast(true);
                    case KeyEvent.VK_P-> Valley.this.paused();
                    case KeyEvent.VK_SPACE-> Valley.this.speeding = true;
//                        Valley.this.centralize();
                    case KeyEvent.VK_C-> Valley.this.setCentered(!Valley.this.keepCentered);
                    case KeyEvent.VK_NUMPAD1 -> Valley.this.moveCam(-8,  8);
                    case KeyEvent.VK_NUMPAD2 -> Valley.this.moveCam( 0,  8);
                    case KeyEvent.VK_NUMPAD3 -> Valley.this.moveCam( 8,  8);
                    case KeyEvent.VK_NUMPAD4 -> Valley.this.moveCam(-8,  0);
                    case KeyEvent.VK_NUMPAD6 -> Valley.this.moveCam( 8,  0);
                    case KeyEvent.VK_NUMPAD7 -> Valley.this.moveCam(-8, -8);
                    case KeyEvent.VK_NUMPAD8 -> Valley.this.moveCam( 0, -8);
                    case KeyEvent.VK_NUMPAD9 -> Valley.this.moveCam( 8, -8);
                    case KeyEvent.VK_NUMPAD5 -> Valley.this.centralize();
                }
            }
            @Override public void keyReleased(KeyEvent ke){
                switch(ke.getKeyCode()){
                    case KeyEvent.VK_W-> Valley.this.PLAYER.setNort(false);
                    case KeyEvent.VK_A-> Valley.this.PLAYER.setWest(false);
                    case KeyEvent.VK_S-> Valley.this.PLAYER.setSout(false);
                    case KeyEvent.VK_D-> Valley.this.PLAYER.setEast(false);
                    case KeyEvent.VK_SPACE-> Valley.this.speeding = false;
                }
            }
        });
    }
    private void createGrid(int cols, int rows){
        final BufferedImage shiny = elfactory.Materializing.getImage(1),
                fortres = elfactory.Materializing.getImage(5);
        final var main = this.getMain();
        final int len = cols * rows, w = 64, h = 64;
        Terrain<?> backTerrain = null;
        entities.Stack<?,?> stak;
        for(int i = 0; i < len; i++){
            final int col = i%cols, row = i/cols;
            final int xx = col * w, yy = row * h;
            final boolean active = random().nextInt(10) >= 6;
            
            if(active) backTerrain = new ActiveTerrain(new Rectangle(xx, yy, w, h)).setImage(fortres);
            else {
//                backTerrain = new Terrain(new Rectangle(xx, yy, w, h)).setImage(shiny);
                //the two corner-terrains that deciedes grid bounds
                if(!(i == 0 || i == len - 1) && random().nextInt(12) > 6){
                    if(random().nextBoolean())
                        stak = new Apples(random().nextInt(5, 19), xx, yy, w, h);
                    else stak = new Rocks(random().nextInt(2, 11), xx, yy, w, h);
                    this.consumables.add(stak);
                }
                else backTerrain = new Terrain(new Rectangle(xx, yy, w, h)).setImage(shiny);
            }
            if(backTerrain != null) main.add(backTerrain); 
        }
    }
    private static boolean outBounds(List<Bounded<?>> lst, Bounded otr){
        final int len = lst.size();
        if(len == 0)return true;
        final Bounded first = lst.getFirst(), last = lst.getLast();
        if(first == last) return !first.boundary.rectangle().contains(otr.boundary.rectangle());
        final Point uplf = first.boundary.point(), dnrt = last.boundary.otherPoint();
        int width  = dnrt.x - uplf.x;
        int height = dnrt.y - uplf.y;
        Rectangle bounds = new Rectangle(uplf.x, uplf.y, width, height);
        return !(bounds.contains(otr.boundary.rectangle()));
    }
    private void paused(){
        if(Launcher.LOOP.isRunning()){
            this.pause = null;
        }else{
            this.centralize();
            this.pause = new Message("Paused!", 48, this.PLAYER.boundary.scale(Launcher.escalator));
            Launcher.repaint();
        }
    }
    public void setCentered(boolean centralize){this.keepCentered = centralize;}
    public void centralize(){
        this.getView().getBounds().setCenter(this.PLAYER.boundary.getCenter().scale(Launcher.escalator));
//        Launcher.repaint();
    }
    private void moveCam(int xx, int yy){
        final var vew = this.getMain().getView();
        vew.getBounds().setCenter(vew.getBounds().getCenter().add(xx, yy));
    }
//    public boolean isCentered(){
//        return this.PLAYER.boundary.getCenter().equals(this.getMain().getView().getBounds().getCenter());
//    }
    private static java.util.concurrent.ThreadLocalRandom random(){
        return java.util.concurrent.ThreadLocalRandom.current();
    }
    private boolean keepCentered = true;
    private boolean speeding = false;
    public final Lock lockCons;
    private Message pause;
    private final Player PLAYER;
    private List<entities.Stack<?,?>> consumables;
}