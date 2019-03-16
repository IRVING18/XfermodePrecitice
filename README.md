# DashBoardView 基本表盘

**主要包含知识点：**
- 1.PathDashPathEffect 的使用。
- 2.PathMeasure 的使用
- 3.三角函数的一部分知识

## 一、思路
- 1.画一个弧线，canvas.drawArc()就ok
- 2.画上刻度，画刻度，需要知道弧线总长PathMearsure.getlenth()
- 3.使用paint.setPathEffect()间隙用来画刻度。
- 4.PathDashPathEffect(Path shape, float advance, float phase,Style style)
来添加具体的刻度的形状，间隙等
- 5.drawLine画指针

## 二、画弧线 drawArc()
![linear](https://github.com/IRVING18/notes/blob/master/android/file/angle.png)
> 注意:代码中ANGLE是代表图中下边小角的角度。

```java
    public static final int   ANGLE  = 120;
    
    ondraw()方法：
    //划弧线
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE, false, paint);
```

## 三、画刻度 paint.setPathEffect()
> PathDashPathEffect使用具体看：[PathEffect](https://github.com/IRVING18/notes/blob/master/android/自定义View/2、Paint.md)

