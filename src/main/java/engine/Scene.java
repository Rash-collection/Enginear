/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package engine;

import funks.Viewable;
import funks.Renderer;
import funks.Resizer;
import funks.Updater;
import funks.PreLoader;
import funks.PostLeaver;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import means.Launcher;

/**
 *
 * @author rash4
 */
public class Scene <T extends Scene<T>>{
    public final static Renderer     DEF_RENDERER  = grr->{};
    public final static PostLeaver   DEF_POSTLEAVE = ()->{};
    public final static Resizer      DEF_RESIZER = ()->{};
    public final static Updater      DEF_UPDATER = ()->{};
    public final static PreLoader    DEF_PRELOAD = ()->{};
    public final static Scene        ZERO = emptyScene("ZERO");
    static{
        ZERO.setPreLoader(ZERO::setTitle);
        // just for fun debugging
//        System.out.println("||ZERO has default labda!? " + ZERO.hasDefaultLambda() + " ||");
//        System.out.println("||ZERO is empty scene!? " + ZERO.isEmptyScene() + " ||");
    }
    
    public Scene(String name){
        this.name = name;
        this.UNITS = new ArrayList<>();
    }
    
    public T self(){return (T)this;}
    
    public void setTitle(){Launcher.GAME.setTitle(Launcher.title() + "-" + this.name);}
    public T add(){
        Launcher.addScene(this.self());
        return this.self();
    }
    
    public boolean hasKeys()    {return this.keys != null;}
    public boolean hasMouse()   {return this.mouse != null;}
    public boolean hasMotion()  {return this.motion != null;}
    public boolean hasWheel()   {return this.wheeler != null;}
    
    public boolean hasDefaultLambda(){
        return (this.postLeave  == DEF_POSTLEAVE ||
                this.preLoad    == DEF_PRELOAD   ||
                this.render     == DEF_RENDERER  ||
                this.update     == DEF_UPDATER   ||
                this.resize     == DEF_RESIZER   );
    }
    public boolean isEmptyScene(){
        return (this.postLeave  == DEF_POSTLEAVE &&
                this.preLoad    == DEF_PRELOAD   &&
                this.render     == DEF_RENDERER  &&
                this.update     == DEF_UPDATER   &&
                this.resize     == DEF_RESIZER   );
    }
    
    public static Scene emptyScene(String name){
        final var scene = new Scene(name);
        scene  
                .setRenders     (DEF_RENDERER)
                .setUpdates     (DEF_UPDATER)
                .setResizer     (DEF_RESIZER)
                .setPreLoader   (DEF_PRELOAD)
                .setPostLeave   (DEF_POSTLEAVE)
                ;
        return scene;
    }
    public final boolean onLeave(JPanel owner){
        if(this.keys != null)owner.removeKeyListener(this.keys);
        if(this.mouse != null)owner.removeMouseListener(this.mouse);
        if(this.wheeler != null)owner.removeMouseWheelListener(this.wheeler);
        if(this.motion != null)owner.removeMouseMotionListener(this.motion);
        if(this.postLeave != null)this.postLeave.postLeave();
        return true;
    }
    public final boolean onLoad(JPanel owner){
        Launcher.focus();
        if(this.preLoad != null)this.preLoad.preLoad();
        if(this.keys != null)owner.addKeyListener(this.keys);
        if(this.mouse != null)owner.addMouseListener(this.mouse);
        if(this.wheeler != null)owner.addMouseWheelListener(this.wheeler);
        if(this.motion != null)owner.addMouseMotionListener(this.motion);
        this.resize.resize();
        owner.repaint();
        return true;
    }
    public final boolean onLoad(JPanel owner, boolean doFollowUps){
        if(this.preLoad != null)this.preLoad.preLoad();
        if(this.keys != null)owner.addKeyListener(this.keys);
        if(this.mouse != null)owner.addMouseListener(this.mouse);
        if(this.wheeler != null)owner.addMouseWheelListener(this.wheeler);
        if(this.motion != null)owner.addMouseMotionListener(this.motion);
        if(!doFollowUps){
            Launcher.focus();
            this.resize.resize();
            owner.repaint();
        }
        return true;
    }
    public T setUpdates(Updater updates){
        
        this.update = updates;
        return this.self();
    }
    public T setRenders(Renderer renders){
        this.render = renders;
        return this.self();
    }
    public T setViewable(Viewable renders){
        this.render = renders;
        return this.self();
    }
    public T setResizer(Resizer resize){
        this.resize = resize;
        return this.self();
    }
    public T setPreLoader(PreLoader load){
        this.preLoad = load;
        return this.self();
    }
    public T setPostLeave(PostLeaver leave){
        this.postLeave = leave;
        return this.self();
    }
    public T setKeys(KeyListener keys){
        this.keys = keys;
        return this.self();
    }
    public T setMouse(MouseListener mouse){
        this.mouse = mouse;
        return this.self();
    }
    public T setMotion(MouseMotionListener motion){
        this.motion = motion;
        return this.self();
    }
    public T setWheel(MouseWheelListener wheel){
        this.wheeler = wheel;
        return this.self();
    }
    public T setMouseAdepter(MouseAdapter adapt){
        return this.self().setMouse(adapt).setMotion(adapt).setWheel(adapt);
    }
    
    public String name(){return this.name;}
    
    public void addStack(entities.Bounded<?> entity) {this.UNITS.add(entity);}
    public T add(entities.Bounded<?> entity) {
        this.UNITS.add(entity);
        return self();
    }
    
    public List<entities.Bounded<?>> getEntities(){return this.UNITS;}
    
    public PostLeaver   getLeaver() {return this.postLeave;}
    public PreLoader    getLoader() {return this.preLoad;}
    public Updater      getUpdates(){return this.update;}
    public Renderer     getRender() {return this.render;}
    public Resizer      getResizer(){return this.resize;}
    
    public KeyListener          getKeys(){return this.keys;}
    public MouseListener        getMouse(){return this.mouse;}
    public MouseMotionListener  getMotion(){return this.motion;}
    public MouseWheelListener   getWheel(){return this.wheeler;}
    
    private final String name;
    
    Updater     update;
    Renderer    render;
    Resizer     resize;
    PostLeaver  postLeave;
    PreLoader   preLoad;
    
    KeyListener         keys;
    MouseListener       mouse;
    MouseMotionListener motion;
    MouseWheelListener  wheeler;
    
    @annots.LifeTime("type 'Bounded<?>' is TEMPO for now,"
            + "till it's necessary to turn it to background 'Terrain<?>'")
    private final List<entities.Bounded<?>> UNITS;
}