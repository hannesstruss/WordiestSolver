# Wordiest Solver
Solver for one of my favorite Android games, [Wordiest](https://play.google.com/store/apps/details?id=com.concreterose.wordiest).

Don't use this for cheating!

## Example

```
hannes@nestor:~/code/WordiestScala$ sbt run
Loading dictionary...
Hello!
Enter some letters:
> a f n d,3w o m,4l e z
Solving List(Tile(a,1,1), Tile(f,1,1), Tile(n,1,1), Tile(d,1,3), Tile(o,1,1), Tile(m,4,1), Tile(e,1,1), Tile(z,1,1))
m,4l o n a d,3w
f e z
66 + 15 = 81
```

## TODO

- Use a [Trie](http://en.wikipedia.org/wiki/Trie) instead of just throwing every word and their substrings into a Set
- Generate inflections of words (plurals, tenses, ...)

