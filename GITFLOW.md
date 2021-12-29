## Работа с ветками ##

* ветка master - ее не трогаем
* ветка develop - основная, в которой будет вестись работа

Порядок работы.
1. От ветки develop создаешь новую ветку со следующим названием ft/<номер задачи>_<краткое описание>.
   Номер задачи: берется из названия задачи. Например "CC-621 Тестовая задача". Тут номер "CC-621".
   Краткое описание: короткое описание на английском. Например "test_task" или "create_auth_ui".
   В общем виде получится примерно следубщее: ft/CC-621_test_task.
2. При сохранении коммита оставляй понятное НО краткое описание проделанной работы.
3. Как только задача будет готова, создаешь pr (Pull Request) в ветку develop.
4. В насктройках pr ставишь меня "Assigner" и "Reviewer" (если правильно помню названия).
5. Как только pr будет создан, кидаешь мне ссылку в телеграм. Я посмотрю pr.
6. После внесения правок в pr (если они будут) я солью ветку в develop.