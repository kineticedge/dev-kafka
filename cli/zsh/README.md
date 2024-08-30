
# Script

* Create `~/.zsh/site-functions` or equivalent path to your liking

* Copy `site-functions` here into `~/.zsh/site-functions`

* Rename `_` files to match the distribution of Kafka

  * for example if you install Apache Kafka distribution, rename `_kafka-topics` to `_kafka-topics.sh`.

```
compinit -D
```

* Modify `.zshrc` 

```
fpath=(~/.zsh/site-functions $fpath)
autoload -Uz compinit compsys && compinit
```
