# 감정표현 플러그인
___
특정 문자를 입력하거나 특정 아이템을 우클릭 하여 감정표현을 실행시킬 수 있다.

 나만의 감정표현 만들기 예시
```java
((Emotions) Bukkit.getPluginManager().getPlugin("Emotions")).getEmoteManager().registerEmotion(e -> {
    e.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, e.getLocation(), 100, 0.1, 0.1 ,0.1);
}, "HAPPY");
```
UseEmoteEvent사용 예시
```java
@EventHandler
public void onEmote(UseEmoteEvent e) {
    if(e.getType() == EmoteType.CHAT && e.getChat().equals("HAPPY")) e.setCancelled(true); //감정표현 사용을 취소한다
}
```
