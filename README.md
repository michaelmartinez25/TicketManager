# project1-10 #

NOTE: If you are having trouble with eclipse recognizing packages/tests correctly: right click on project in eclipse finder bar on left, click Refresh Project. Then, right click on the project again, go to Maven->Update Project. 

Expectation: Include Javadocs while stubbing out classes

Create packages, import provided classes, push to git | 2 | no dependencies | Zeb | 30 Sept | Done 9/30

Stub out the Ticket class | 5 | Will depend on enumeration and ticket states; can stub without | Zeb | 4 October | Done 9/30

Stub out the TicketManager class | 2 | No dependencies | Michael | 4 October | Done 9/30

Stub out the TicketWriter and TicketReader | 1 | No dependencies | Michael | 4 October | Done 10/3

Stub out the TicketList | 2 | Will depend on ticket; can stub without | Michael | 4 October | Done 10/3

Stub out the Enumerations | 3 | No dependencies | Zeb | 4 October | Done 10/1

Stub out the Command class | 2 | Will depend on the enumerations; can stub without | Zeb | 4 October | Done 10/1

Stub out the Ticket's State classes| 4 | No dependencies | Michael | 4 October | Done 10/3 

Run CheckStyle and PMD/Ensure GitHub Actions runs properly | 3 | Dependent on skeleton being complete | Michael | 7 October | Done 10/7

---

Expectation:  > 80% coverage for each class before task is done. 

Write unit tests for Command class | 4 | Depends on skeleton | Zeb | 13 October | Done 10/9
Implement Command class, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 5 | Zeb | 13 october | Done 10/9

Write unit tests for Ticket class | 7 | Depends on skeleton | Zeb | 13 October | Done 10/12
Implement Ticket class, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 8 | Zeb | 13 october | Done 10/18

Implement State interface | 3 | Depends on skeleton | N/A | 13 October | Done

Write unit tests for Ticket.NewState class | 3 | Depends on skeleton, State interface | Michael | 13 October | Done 10/13
Implement NewState class, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 4 | Michael | 13 october | Done 10/14

Write unit tests for Ticket.FeedbackState class | 3 | Depends on skeleton, State interface | Michael | 13 October | Done 10/13
Implement FeedbackState class, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 4 | Michael | 13 october | Done 10/14

Write unit tests for Ticket.WorkingState class | 3 | Depends on skeleton, State interface | Michael | 13 October | Done 10/13
Implement WorkingState class, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 4 | Michael | 13 october | Done 10/14

Write unit tests for Ticket.ClosedState class | 3 | Depends on skeleton, State interface | Michael | 13 October | Done 10/13
Implement ClosedState class, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 4 | Michael | 13 october | Done 10/14

Write unit tests for Ticket.CanceledState class | 3 | Depends on skeleton, State interface | Michael | 13 October | Done 10/13
Implement CanceledState class, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 4 | Michael | 13 october | Done 10/14

Write unit tests for Ticket.ResolvedState class | 3 | Depends on skeleton, State interface | Michael | 13 October | Done 10/13
Implement ResolvedState class, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 4 | Michael | 13 october | Done 10/14

---

Write unit tests for TicketList | 4 | Depends on Ticket, TicketList| Michael | 18 October | Done 10/20
Implement TicketList, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 6 | Michael | 17 October | Done 10/20

Write unit tests for TicketReader | 2 | Depends on Ticket, TicketList | Zeb | 18 October | Done 10/19
Implement TicketReader, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 6 | Zeb | 17 October | Done 10/19

Write unit tests for TicketWriter | 2 | Depends on Ticket, TicketList| Zeb | 18 October | Done 10/20
Implement TicketWriter, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 6 | Zeb | 17 October | Done 10/20

Write unit tests for TicketManager | 6 | Depends on Ticket, TicketList| Michael | 19 October | Done 10/24
Implement TicketManager, ensure it passes all unit tests, PMD/Checkstyle, TS tests | 8 | Michael | 19 October | Done 10/24

---

First pass of system test, debug any issues found | 4 | depends on implementation being complete. | Michael | 21 October | Done 10/24

Review and update javadoc to ensure that it meets standards and reflects final implementation | 4 | Depends on implementation being completed. | Zeb | 21 October | Started 10/21

Run javadoc utility, ensure everything looks good, commit doc folder to GitHub | Depends on implementation being complete and javadoc review | Zeb | 21 October  | Not Started

Final check for FindBugs, PMD, Checkstyle errors as recorded by Github Actions | 2 | Depends on implementation being complete | Zeb | 21 October | Not started

Final system test, record results and submit as pdf in project_docs folder on Github| 3 | Depends on implementation being complete | Michael | 21 October | Not Started

---

Final submission by the 24th



